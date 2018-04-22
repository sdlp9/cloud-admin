layui.use(['form', 'upload','layedit','layer','table','laydate'], function(){
    var form = layui.form,
        upload = layui.upload,
        table = layui.table,
        laydate  = layui.laydate,
        layedit = layui.layedit,
        layer = layui.layer,
        $ = layui.$;
    table.render({
        elem: '#user-table',
        url: '/user/list',
        where: {sidx: 'createTime', order: "desc"},
        cols: [[
            {type:'checkbox'},
            {field:'userId', title: 'ID', sort: true},
            {field:'username',title: '用户名'},
            {field:'email', title: '邮箱', sort: true},
            {field:'mobile', title: '电话'},
            {field:'status', title: '状态',templet: function(d){
                    if(d.status === 1){
                        return "正常"
                    }else{
                        return "已删除"
                    }
                }
            },
            {field:'createTime', title: '创建时间', sort: true}
        ]],
        id: 'userTable',
        page: true,
        limits: [20,30,50,100],
        limit: 10 //默认采用10
    });
    var methods = {
        fn_search : function () {
            var username = $('#userName');
            //执行重载
            table.reload('userTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    username: username.val()
                }
            });
        },
        fn_add : function () {
            window.location.href = "/user/goAdd";
        }
    };
    $('.layui-btn').on('click', function(){
        var type = $(this).data('type');
        methods[type] ? methods[type].call(this) : '';
    });
});