<#--
使用者可以通过覆盖这个文件实现对一些宏的重新定义包括：
--html head中的内容，包括css meta等
<#macro mower_front_head title = ''>
--页面的最上端
<#macro mower_front_header_login>
--广告
<#macro mower_front_header_ad>
--LOGO
<#macro mower_front_header_logo>
--菜单栏
<#macro mower_front_header_menu>
--页脚
<#macro mower_front_footer>
--包含的Javascript(angularjs,knockoutjs,datagrid)
<#macro mower_front_scripts require = ''>
-->


<#--
局部替换：如果以下变量定义在具体业务模板中，则会覆盖你的layout模板中的定义

-- 定义最顶的登录状态条最左边显示的文字内容
<#global mower_front_header_login_custom_left>
	文字
</#global>

-- 定义最顶的登录状态条最右边显示的文字内容
<#global mower_front_header_login_custom_right>
	<li>
		<a target="_blank" href="#"><i class="fa fa-sitemap"></i>网站导航</a>
	</li>
</#global>

定制广告头
<#global mower_front_header_ad_custom>

</#global>

定制LOGO区域
<#global mower_front_header_logo_custom>

</#global>

定制整个菜单区域
<#global mower_front_header_menu_custom>
xxxxx
</#global>

定制默认菜单的右边区域
<#global mower_front_header_menu_custom_right>
    <li><a href="xxx">xxx</a></li>
</#global>

定制页脚区域
<#global mower_front_footer_custom>
ddddadf
</#global>

<#global mower_front_scripts_addition>
	加入你自己的javascript库文件
</#global>

<#global mower_front_head_addition>
	加入你自己的css文件
</#global>
-->