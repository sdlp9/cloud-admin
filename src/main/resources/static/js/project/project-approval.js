layui.use(['form', 'table'], function(){
    var form = layui.form,
        table = layui.table,
        $ = layui.$;
    table.render({
        elem: '#approval-table',
        url: '/project/examinelist',
        where: {sidx: 'createTime', order: "desc"},
        cols: [[
            {type:'checkbox'},
            {field:'id', title: 'ID', sort: true},
            {field:'projectName',title: '项目名称'},
            {field:'projectCost',title: '项目成本'},
            {field:'projectHeader',title: '项目发起人'},
            {field:'financingMoney',title: '筹资金额'},
            {field:'startTime',title: '筹资开始'},
            {field:'endTime',title: '筹资结束'},
            {field:'projectType', title: '项目类型', sort: true,templet: function(d){
                    if(d.projectType === 1){
                        return "网络电影"
                    }else{
                        return "院线电影"
                    }
                }
            },
            {field:'projectStatus', title: '项目状态', sort: true,templet: function(d){
                    if(d.projectStatus === 1){
                        return "即将上线"
                    }
                    if(d.projectStatus === 2){
                        return "正在募集"
                    }
                    if(d.projectStatus === 3){
                        return "募集完成"
                    }
                }
            },
            {field:'examineStatus', title: '审核状态',templet: function(d){
                    if(d.examineStatus === 1){
                        return '<span class="label label-primary">未提审</span>';
                    }
                    if(d.examineStatus === 2){
                        return '<span class="label label-info">已提交未审核</span>';
                    }
                    if(d.examineStatus === 3){
                        return '<span class="label label-danger">驳回</span>';
                    }
                    if(d.examineStatus === 4){
                        return '<span class="label label-success"> 审核通过</span>';
                    }
                }
            },
            {field:'publishStatus', title: '是否发布',templet: function(d){
                    if(d.publishStatus === 1){
                        return '<span class="label label-primary">是</span>';
                    }
                    if(d.publishStatus === 2){
                        return '<span class="label label-info">否</span>';
                    }
                }
            },
            {field:'createTime', title: '创建时间', sort: true}
        ]],
        id: 'approvalTable',
        page: true,
        limits: [10,20,50,100],
        limit: 10 //默认采用10
    });
    form.on('select(examineStatus)', function(data){
        vm.project.examineStatus = data.value;
    });
});
var vm = new Vue({
    el:'#project-approval',
    data:{
        q:{
            projectName: null
        },
        title: '项目列表',
        showForm: false,
        project: {}
    },
    updated: function(){
        layui.form.render();
    },
    methods: {
        fn_search : function () {
            //执行重载
            layui.table.reload('approvalTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    projectName: vm.q.projectName
                }
            });
        },
        query: function () {
            vm.showForm = false;
            vm.fn_search();
        },
        fn_examine : function () {
            vm.title = '项目审核';
            var checkStatus = layui.table.checkStatus('approvalTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择需要审核的项目");
                return false;
            }
            if(data.length > 1){
                layui.layer.msg("只能选择一项进行审核");
                return false;
            }
            vm.getProject(data[0].id);
            vm.showForm = true;
        },
        saveOrUpdate: function () {
            var url = "/project/examine";
            if(vm.validator()){
                return ;
            }
            var approvalFrom = {};
            approvalFrom.id = vm.project.id;
            approvalFrom.examineStatus = vm.project.examineStatus;
            approvalFrom.remark = vm.project.remark;
            layui.$.ajax({
                type: "POST",
                contentType: "application/json",
                url: url,
                data: JSON.stringify(approvalFrom),
                success: function(r){
                    if(r.code === 0){
                        //入库成功，
                        layui.layer.msg('操作成功', {icon: 1});
                        vm.query();
                    }else{
                        layui.layer.msg(r.msg);
                    }
                }
            });
        },
        getProject: function(id){
            layui.$.get("/project/info/"+id, function(r){
                vm.project = r.project;
            });
        },
        //判断是否为空
        isBlank: function (value) {
            return !value || !/\S/.test(value)
        },
        validator: function () {
            if(vm.project.examineStatus === "" || vm.project.examineStatus === undefined){
                layer.msg('请选择审核结果！', {icon: 0});
                return true;
            }
        }
    }
});