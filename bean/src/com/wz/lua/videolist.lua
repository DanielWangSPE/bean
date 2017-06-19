-- 引入依赖库
local cjson = require "cjson"
local mysql = require "mysql"

-- pc端登录用户的共享内存
local pclogin_store = ngx.shared.pclogin_store

-- 获取get参数列表
local args = ngx.req.get_uri_args()
local userId = args.userId
local role_key = args.role_key
local component_type = args.component_type
ngx.log(ngx.CRIT, userId..role_key..component_type)

-- 判断该userId的登录状态
local login = pclogin_store:get(userId)
ngx.log(ngx.CRIT, login)
local list = {}

-- 登录方可进行sql查询
if login then
    -- 创建mysql实例
    local db = mysql:new()

    --查询sql
    local videolist_sql = "SELECT tmp.channel_key AS channelKey, c.classify_name AS channelName, tmp.column_key AS columnKey, tmp.column_name AS columnName FROM ( SELECT c.classify_key AS column_key, c.classify_name AS column_name, c.parent_key AS channel_key FROM t_role_classify rc INNER JOIN t_classify c ON rc.classify_key = c.classify_key WHERE role_key = '"..role_key.."' AND component_type = '"..component_type.."' ) tmp INNER JOIN t_classify c ON tmp.channel_key = c.classify_key"
    local res, err, errno, sqlstate = db:query(videolist_sql)
    if not res then
    ngx.say("select error : ", err, " , errno : ", errno, " , sqlstate : ", sqlstate)
    end

    if res then
        for i, row in ipairs(res) do
            local video = {}
            video.channelKey = row.channelKey
            video.channelName = row.channelName
            video.columnKey = row.columnKey
            video.columnName = row.columnName
            list[i] = video
        end
    end
    db:close()
end


-- 定义响应体
local status_code = ""
local ret = ""
local msg = ""
if login then
    status_code = 200
    ret = 100
    msg = "成功"
else
    status_code = 403
    ret = 102
    msg = "无权限"
end
local request_body = {
    code = status_code;
    results = {
        ret = ret;
        msg = msg;
        list = list;
    }
}
local data = cjson.encode(request_body)
ngx.say(data)

return data