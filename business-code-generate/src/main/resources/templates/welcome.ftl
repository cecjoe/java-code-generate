<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>layout 后台大布局 - Layui</title>
    <link rel="stylesheet" href="/layui/css/layui.css">
    <style type="text/css">
        .layadmin-iframe {
            position: absolute;
            width: 100%;
            height: 100%;
            left: 0;
            top: 0;
            right: 0;
            bottom: 0;
        }

    </style>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">业务代码自动生成平台</div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="">控制台</a></li>
<#--            <li class="layui-nav-item"><a href="">商品管理</a></li>-->
<#--            <li class="layui-nav-item"><a href="">用户</a></li>-->
<#--            <li class="layui-nav-item">-->
<#--                <a href="javascript:;">其它系统</a>-->
<#--                <dl class="layui-nav-child">-->
<#--                    <dd><a href="">邮件管理</a></dd>-->
<#--                    <dd><a href="">消息管理</a></dd>-->
<#--                    <dd><a href="">授权管理</a></dd>-->
<#--                </dl>-->
<#--            </li>-->
        </ul>
        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">
                    <img src="http://t.cn/RCzsdCq" class="layui-nav-img">
                    贤心
                </a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>

    <div class="layui-side layui-bg-black">
        <div class="layui-side-scroll">
            <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
            <ul class="layui-nav layui-nav-tree"  lay-filter="test">
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">系统管理</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/project.cp" target="main-content">模板配置</a></dd>
                        <dd><a href="/project.cp" target="main-content">角色管理</a></dd>
                        <dd><a href="/project.cp" target="main-content">用户管理</a></dd>
                        <dd><a href="/project.cp" target="main-content">项目配置</a></dd>
                        <#--                        <dd><a href="javascript:;">列表三</a></dd>-->
                        <#--                        <dd><a href="">超链接</a></dd>-->
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">业务生产</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/generate.cp" target="main-content">预处理</a></dd>
                        <dd><a href="/table_relate.cp" target="main-content">表间关联</a></dd>
                        <dd><a href="/export_code.cp" target="main-content">代码生成</a></dd>
<#--                        <dd><a href="javascript:;">列表三</a></dd>-->
<#--                        <dd><a href="">超链接</a></dd>-->
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">数据库设计</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/project.cp" target="main-content">数据表管理</a></dd>
                        <dd><a href="/generate.cp" target="main-content">表间关联</a></dd>
                        <#--                        <dd><a href="/table_relate.cp" target="main-content">表间关联</a></dd>-->
<#--                        <dd><a href="/export_code.cp" target="main-content">代码生成</a></dd>-->
                        <#--                        <dd><a href="javascript:;">列表三</a></dd>-->
                        <#--                        <dd><a href="">超链接</a></dd>-->
                    </dl>
                </li>
                <li class="layui-nav-item layui-nav-itemed">
                    <a class="" href="javascript:;">系统统计</a>
                    <dl class="layui-nav-child">
                        <dd><a href="/project.cp" target="main-content">日常使用统计</a></dd>
                        <dd><a href="/project.cp" target="main-content">系统活跃统计</a></dd>
                        <#--                        <dd><a href="javascript:;">列表三</a></dd>-->
                        <#--                        <dd><a href="">超链接</a></dd>-->
                    </dl>
                </li>
<#--                <li class="layui-nav-item">-->
<#--                    <a href="javascript:;">解决方案</a>-->
<#--                    <dl class="layui-nav-child">-->
<#--                        <dd><a href="javascript:;">列表一</a></dd>-->
<#--                        <dd><a href="javascript:;">列表二</a></dd>-->
<#--                        <dd><a href="">超链接</a></dd>-->
<#--                    </dl>-->
<#--                </li>-->
<#--                <li class="layui-nav-item"><a href="">云市场</a></li>-->
<#--                <li class="layui-nav-item"><a href="">发布商品</a></li>-->
            </ul>
        </div>
    </div>

    <div class="layui-body">
        <iframe frameborder="0" class="layadmin-iframe" name="main-content">

        </iframe>
    </div>

    <div class="layui-footer">
        <!-- 底部固定区域 -->
        © 2020 - 预印开发
    </div>
</div>
<script src="/layui/layui.js"></script>
<script>
    //JavaScript代码区域
    layui.use('element', function(){
        var element = layui.element;

    });
</script>
</body>
</html>