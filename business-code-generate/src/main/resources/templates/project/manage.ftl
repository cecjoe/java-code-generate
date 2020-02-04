<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="/layui/css/layui.css">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
<div class="layui-fluid">
    <div class="layui-card">
        <div class="layui-form layui-card-header layuiadmin-card-header-auto" lay-filter="layadmin-useradmin-formlist">
        </div>
        <form class="layui-form" lay-filter="component-form-group" id="submits" onsubmit="return false">

            <div class="layui-inline">
                <label class="layui-form-label">名称：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" id="name" value="" placeholder="请输入名称" autocomplete="off" class="layui-input">
                </div>
            </div>
            <div class="layui-inline">
                <button class="layui-btn" data-type="reloadlstBond" id="reloadlstBond">搜索</button>
            </div>
        </form>
        <hr>
        <div class="layui-card-body">
            <div style="padding-bottom: 10px;text-align: right">
                <a class="layui-btn layuiadmin-btn-useradmin" data-type="add" id="addbondTemplate">添加项目</a>
            </div>
            <table id="test" lay-filter="LAY-bondTemplate-lst-manage"></table>
            <script type="text/html" id="table-bondTemplate-lst">
                <a class="layui-btn layui-btn-normal layui-btn-xs" lay-event="bondTemplateEdit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="bondTemplateDel"><i
                            class="layui-icon layui-icon-delete"></i>删除</a>
            </script>
        </div>
    </div>
</div>

<script type="text/template" id="bondTemplateList">
    <div class="layui-card-body" style="padding: 15px;">
        <form class="layui-form" lay-filter="component-form-group" id="submits" onsubmit="return false">
            <div class="layui-row layui-col-space10 layui-form-item">
                <input type="hidden" name="id" value="{{ d.id }}">
                <div class="layui-col-lg12">
                    <label class="layui-form-label">项目名称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="sysName" placeholder="请输入名称" autocomplete="off"
                               lay-verify="required" class="layui-input"
                               value="{{ d.sysName?d.sysName:'' }}" {{# if(d.id){ }}disabled{{# } }}>
                    </div>
                </div>
                <div class="layui-col-lg12">
                    <label class="layui-form-label">库链接：</label>
                    <div class="layui-input-block">
                        <input type="text" name="dbUrl" placeholder="请输入库链接" autocomplete="off"
                               lay-verify="required" class="layui-input"
                               value="{{ d.dbUrl?d.dbUrl:'' }}" >
                    </div>
                </div>
                <div class="layui-col-lg12">
                    <label class="layui-form-label">库名称：</label>
                    <div class="layui-input-block">
                        <input type="text" name="dbName" placeholder="请输入库名" autocomplete="off"
                               lay-verify="required" class="layui-input"
                               value="{{ d.dbName?d.dbName:'' }}" >
                    </div>
                </div>
                <div class="layui-col-lg6">
                    <label class="layui-form-label">用户名：</label>
                    <div class="layui-input-block">
                        <input type="text" name="userName" placeholder="请输入用户名" autocomplete="off"
                               lay-verify="required" class="layui-input"
                               value="{{ d.userName?d.userName:'' }}" >
                    </div>
                </div>
                <div class="layui-col-lg6">
                    <label class="layui-form-label">密码：</label>
                    <div class="layui-input-block">
                        <input type="text" name="userPass" placeholder="请输入密码" autocomplete="off"
                               lay-verify="required" class="layui-input"
                               value="{{ d.userPass?d.userPass:'' }}">
                    </div>
                </div>
                <div class="layui-col-lg6">
                    <label class="layui-form-label">包路径：</label>
                    <div class="layui-input-block">
                        <input type="text" name="rootPath" placeholder="请输入包路径" autocomplete="off"
                               lay-verify="required" class="layui-input"
                               value="{{ d.rootPath?d.rootPath:'' }}">
                    </div>
                </div>
            </div>
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit="" lay-filter="bond_sumbit">立即提交</button>
                </div>
            </div>
        </form>
    </div>
</script>

<script src="/layui/layui.js"></script>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>
    layui.define(['table', 'form', 'util', 'laytpl'], function (exports) {
        var $ = layui.jquery;
        var table = layui.table;
        var form = layui.form;
        var admin = layui.admin;
        var laytpl = layui.laytpl;

        table.render({
            elem: '#test'
            ,url:'/sysProject/list'
            ,cellMinWidth: 80 //全局定义常规单元格的最小宽度，layui 2.2.1 新增
            ,cols: [[
                {field:'id', width:80, title: 'ID', sort: true}
                ,{field:'sysName', width:80, title: '系统名'}
                ,{field:'dbUrl', width:280, title: '数据库链接'}
                ,{field:'userName', width:80, title: '用户名'}
                ,{field:'userPass', width:80, title: '密码'}
                ,{field:'rootPath', width:180, title: '包路径'}
                ,{field:'dbName', width:180, title: '库名称'}
                ,{field:'stateDesc', width:180, title: '状态'}
                , {title: '操作', align: 'center', toolbar: '#table-bondTemplate-lst', minWidth: 150}
            ]]
            , id: 'bondTemplateList'
            , page: true
        });

        //监听工具条
        table.on('tool(LAY-bondTemplate-lst-manage)', function (obj) {
            var data = obj.data;
            if (obj.event === 'bondTemplateDel') {  //删除

                $.ajax({
                    url: '/sysProject/delete/'+data.id,
                    type: 'DELETE',
                    success: function(res) {
                        if (res.code == '0') {
                            layer.msg("删除成功", function (index) {
                                obj.del()
                                layer.close(index);
                            });
                        } else {
                            layer.msg('删除失败！', {icon: 2, time: 1000});
                        }
                    }
                });
            } else if (obj.event === 'bondTemplateEdit') {

                data.list = layui.data('bondTemplateList').list;
                var content = $("#bondTemplateList").html();
                laytpl(content).render(data, function (string) {
                    layer.open({
                        type: 1,
                        title: "修改项目信息",
                        area: ['1200px', '400px'], //宽高
                        content: string
                    });
                    form.render('select');
                });
            }
            return false;
        });

        $(document).on('click', '#addbondTemplate', function () {
            debugger;
            var data = [];
            data.list = layui.data('bondTemplateList').list;
            var content = $("#bondTemplateList").html();
            laytpl(content).render(data, function (string) {
                layer.open({
                    type: 1,
                    title: "新增项目",
                    area: ['1200px', '400px'], //宽高
                    content: string
                });
                form.render('select');
            });
        });

        /* 监听提交 */
        form.on('submit(bond_sumbit)', function (data) {
            var url = '/sysProject/create';
            var type = 'post';
            if(data.field.id > 0 ){
                type = 'put';
                url = '/sysProject/update';
            }

            $.ajax({
                type : type,
                contentType: "application/json;charset=UTF-8",
                url : url,
                data : JSON.stringify(data.field),
                success : function(res) {
                    if (res.code == '0') {
                        layer.msg('处理成功', {time: 1000}, function () {
                            layer.closeAll();
                            table.reload('bondTemplateList');
                        });
                    } else {
                        layer.msg(res.msg);
                    }
                    // console.log(result);
                },
                error : function(e){
                    console.log(e.status);
                    console.log(e.responseText);
                }
            });
            return false;
        });

        //搜索
        $("#reloadlstBond").on('click',function(){
            var name = $('#name').val();//名称
            table.reload('bondTemplateList', {
                method: 'get'
                , where: {sysName: name}
                , page: {
                    pageNo: 1
                }
            });
        });

        exports('template/bondTemplate', {})
    });

</script>

</body>
</html>