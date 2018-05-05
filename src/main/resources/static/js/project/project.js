var contentEdit;
var layedit;
layui.use(['form', 'table','upload','layedit', 'laydate'], function(){
    var form = layui.form,
        table = layui.table,
        upload = layui.upload,
        $ = layui.$,
        laydate = layui.laydate;
    layedit = layui.layedit;
    table.render({
        elem: '#project-table',
        url: '/project/list',
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
        id: 'projectTable',
        page: true,
        limits: [10,20,50,100],
        limit: 10 //默认采用10
    });
    laydate.render({
        elem: '#projectStartTime',
        done: function(value, date, endDate){
            vm.project.startTime = value;
        }
    });
    laydate.render({
        elem: '#projectEndTime',
        done: function(value, date, endDate){
            vm.project.endTime = value;
        }
    });
    form.on('select(projectType)', function(data){
        vm.project.projectType = data.value;
    });
    form.on('select(indexFlag)', function(data){
        vm.project.indexFlag = data.value;
    });
    form.on('select(projectStatus)', function(data){
        vm.project.projectStatus = data.value;
    });
    //多图片上传
    upload.render({
        elem: '#test2',
        url: '/upload/',
        method: 'post',
        auto: false,
        data: {attach_type: '2'},//附件关联类型
        multiple: true,//是否允许多文件上传。设置 true即可开启
        accept: 'images',//指定允许上传时校验的文件类型
        acceptMime: 'image/*',//规定打开文件选择框时，筛选出的文件类型，值为用逗号隔开的 MIME 类型列表
        exts: 'jpg|png|gif|bmp|jpeg',
        bindAction: '#testListAction',
        before: function(obj){
            //预读本地文件示例，不支持ie8
        },
        allDone: function(obj){ //当文件全部被提交后，才触发
            //console.log(obj.total); //得到总文件数
            //console.log(obj.successful); //请求成功的文件数
            //console.log(obj.aborted); //请求失败的文件数
        },
        done: function(res){
            var pic ={} ;
            pic.filePath = res.path;
            pic.name = res.fileName;
            pic.id = res.attachId;
            vm.picList.push(pic);
        }
    });
    layedit.set({
        uploadImage: {
            url: '/editUpload/',
            type: 'post'
        }
    });
});
var vm = new Vue({
    el:'#project-html',
    data:{
        q:{
            projectName: null
        },
        title: '项目列表',
        showForm: false,
        project: {},
        picList: []
    },
    updated: function(){
        layui.form.render();
    },
    methods: {
        fn_search : function () {
            //执行重载
            layui.table.reload('projectTable', {
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    projectName: vm.q.projectName
                }
            });
        },
        fn_add : function () {
            vm.title = '项目增加';
            vm.project = {};
            vm.picList = [];
            contentEdit = layedit.build('content');
            layedit.setContent(contentEdit, null);
            vm.showForm = true;
        },
        fn_update : function () {
            vm.title = '项目修改';
            var checkStatus = layui.table.checkStatus('projectTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择要修改的数据");
                return false;
            }
            if(data.length > 1){
                layui.layer.msg("只能选择一条数据进行修改");
                return false;
            }
            vm.getProject(data[0].id);
            vm.picList = [];
            //获取图片附件
            vm.getAttachs(data[0].id,2);
            vm.showForm = true;
        },
        query: function () {
            vm.showForm = false;
            vm.fn_search();
        },
        saveOrUpdate: function () {
            var url = vm.project.id == null ? "/project/save" : "/project/update";
            if(vm.validator()){
                return ;
            }
            var attachId = '';
            layui.$.each(vm.picList, function(index, item) {
                attachId = attachId + item.id + ",";
            });
            vm.project.attachId = attachId;
            layui.$.ajax({
                type: "POST",
                url: url,
                data: {
                    id: vm.project.id,
                    projectName: vm.project.projectName,
                    projectCost: vm.project.projectCost,
                    projectDes: layedit.getContent(contentEdit),
                    projectHeader: vm.project.projectHeader,
                    financingMoney: vm.project.financingMoney,
                    startTime: vm.project.startTime,
                    endTime: vm.project.endTime,
                    projectLabel: vm.project.projectLabel,
                    projectCoverCharge: vm.project.projectCoverCharge,
                    projectType: vm.project.projectType,
                    projectStatus: vm.project.projectStatus,
                    indexFlag: vm.project.indexFlag,
                    projectVideo: vm.project.projectVideo,
                    attachId: vm.project.attachId
                },
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
        fn_delete: function(){
            var checkStatus = layui.table.checkStatus('projectTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择需要删除数据");
                return false;
            }
            if(data[0].examineStatus === 2 || data[0].examineStatus === 4){
                layui.layer.msg("项目正在审核中或已审批完毕，不允许删除", {icon: 0});
                return false;
            }
            if(data[0].publishStatus === 1){
                layui.layer.msg("项目已发布，不允许删除", {icon: 0});
                return false;
            }
            layer.confirm('是否确定删除选择的数据？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                var ids = [];
                layui.$.each(data, function(index, item) {
                    ids.push(item.id);
                });
                layui.$.ajax({
                    type: "POST",
                    url: "/project/deleteByIds",
                    contentType: "application/json",
                    data: JSON.stringify(ids),
                    success: function(r){
                        if(r.code === 0){
                            layui.layer.msg('操作成功', {icon: 1});
                            vm.query();
                        }else{
                            layui.layer.msg(r.msg);
                        }
                    }
                });
            });
        },
        getProject: function(id){
            layui.$.get("/project/info/"+id, function(r){
                vm.project = r.project;

                layedit.setContent(contentEdit, vm.project.projectDes);
            });
            contentEdit = layedit.build('content');
        },
        getAttachs: function (projectId,attachType) {
            var params = {
                relId:projectId,
                attachType:attachType
            };
            layui.$.ajax({
                type: "POST",
                url: "/attachs/getAttachsList",
                contentType: "application/json",
                data: JSON.stringify(params),
                success: function(r){
                    if(r.code === 0){
                        var list = r.list;
                        layui.$.each(list, function(index, item) {
                            var pic ={} ;
                            pic.filePath = item.filePath;
                            pic.name = item.name;
                            pic.id = item.id;
                            vm.picList.push(pic);
                        });
                    }else{
                        layui.layer.msg(r.msg);
                    }
                }
            });
        },
        //判断是否为空
        isBlank: function (value) {
            return !value || !/\S/.test(value)
        },
        delete_pic: function (id) {
            layui.$("#"+id).remove();
            layui.$.each(vm.picList, function(index, item) {
                if (item.attachId === id){
                    vm.picList.splice(i, 1);
                }
            });
            var url = '/attachs/deleteById';
            layui.$.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(id),
                success: function(r){
                    if(r.code === 0){
                        layui.layer.msg('删除成功', {icon: 1});
                    }else{
                        layui.layer.msg(r.msg);
                    }
                }
            });
        },
        fn_approval: function(){
            var checkStatus = layui.table.checkStatus('projectTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择要提审的数据");
                return false;
            }
            if(data.length > 1){
                layui.layer.msg("只能选择一条数据进行提审");
                return false;
            }
            if(data[0].examineStatus ===2 || data[0].examineStatus === 4){
                layui.layer.msg("项目正在审核中或已审批完毕，不允许重复提审", {icon: 0});
                return false;
            }
            var approvalFrom = {};
            approvalFrom.id = data[0].id;
            approvalFrom.examineStatus = 2;
            layer.confirm('确定要提审选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                layui.$.ajax({
                    type: "POST",
                    url: "/project/approval",
                    contentType: "application/json",
                    data: JSON.stringify(approvalFrom),
                    success: function(r){
                        if(r.code === 0){
                            layer.msg('操作成功', {icon: 1});
                            vm.query();
                        }else{
                            layer.msg(r.msg, {icon: 0});
                        }
                    }
                });
            });
        },
        fn_publish: function () {
            var checkStatus = layui.table.checkStatus('projectTable'),
                data = checkStatus.data;
            if(data.length === 0){
                layui.layer.msg("请选择要发布/下架的数据");
                return false;
            }
            if(data.length > 1){
                layui.layer.msg("只能选择一条数据进行发布/下架");
                return false;
            }
            if(data[0].examineStatus !== 4){
                layui.layer.msg("项目未经过审核，不允许发布/下架！", {icon: 0});
                return false;
            }
            var id = data[0].id;
            layer.confirm('确定要发布/下架选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
                layui.$.ajax({
                    type: "POST",
                    url: "/project/publish",
                    contentType: "application/json",
                    data: JSON.stringify(id),
                    success: function(r){
                        if(r.code === 0){
                            layer.msg('操作成功', {icon: 1});
                            vm.query();
                        }else{
                            layer.msg(r.msg, {icon: 0});
                        }
                    }
                });
            });
        },
        validator: function () {
            var reg=/^\d*(?:\.\d{0,2})?$/;//金额
            if(vm.project.projectName === "" || vm.project.projectName === undefined){
                layer.msg('项目名称不能为空！', {icon: 0});
                return true;
            }
            var cost = vm.project.projectCost;
            if(!reg.test(cost)){
                layer.msg('项目成本输入格式错误，最多有两位小数！', {icon: 0});
                return true;
            }
            if(vm.project.projectHeader === "" || vm.project.projectHeader === undefined){
                layer.msg('项目发起人不能为空！', {icon: 0});
                return true;
            }
            var financingMoney = vm.project.financingMoney;
            if(!reg.test(financingMoney)){
                layer.msg('筹资金额输入格式错误，最多有两位小数！', {icon: 0});
                return true;
            }
            var startTime = vm.project.startTime;
            var endTime = vm.project.endTime;
            if(startTime === "" || startTime === undefined){
                layer.msg('投资开始时间不能为空！', {icon: 0});
                return true;
            }
            if(endTime === "" || endTime === undefined){
                layer.msg('投资结束时间不能为空！', {icon: 0});
                return true;
            }
            if(startTime > endTime){
                layer.msg('投资结束时间不能早于开始时间！', {icon: 0});
                return true;
            }
            var projectCoverCharge = vm.project.projectCoverCharge;
            if(!reg.test(projectCoverCharge)){
                layer.msg('项目服务费输入格式错误，最多有两位小数！', {icon: 0});
                return true;
            }
            if(vm.project.projectType === "" || vm.project.projectType === undefined){
                layer.msg('请选择项目类型！', {icon: 0});
                return true;
            }
            if(vm.project.projectStatus === "" || vm.project.projectStatus === undefined){
                layer.msg('请选择项目状态！', {icon: 0});
                return true;
            }
        }
    }
});