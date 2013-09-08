<g:if test="${!layout_nomainmenu}">
    <div class="row">
        <div class="span12">
            <g:render template="/_menu/menubar"/>
        </div>
    </div>
</g:if>

<!-- print system messages (infos, warnings, etc) - not validation errors -->
<g:if test="${flash.message && !layout_noflashmessage}">
    <div class="alert alert-info">${flash.message}</div>
</g:if>

<div class="container=fluid">

    <div class="row-fluid">
        <div class="span2">

            <div class="well" style="padding: 8px 0;">
                <ul class="nav nav-list">
                    <li class="nav-header">Navigation</li>
                    %{--<li><a href="/promoapi/deviceFamiles/list"><i class="icon-file"></i> Families</a></li>--}%
                    <li><a href="/dtmc/portfolio"><i class="icon-home"></i> Portfolio</a></li>
                    <li><a href="/dtmc/stock"><i class="icon-file"></i> Stock</a></li>
                    <li><a href="/dtmc/trade"><i class="icon-save"></i> Trade</a></li>
                    <li><a href="/dtmc/vanilla"><i class="icon-save"></i> Options</a></li>
                    <li><a href="/dtmc/userIndicators"><i class="icon-save"></i>User Indicators</a></li>

                    <li class="nav-header">Settings</li>
                    <li><a href="/dtmc/indicators"><i class="icon-save"></i> Indicators</a></li>
                    <li><a href="/dtmc/attributes"><i class="icon-save"></i> Attributes</a></li>

                    %{--<li><a href="#">Link</a></li>--}%
                    %{--<li><a href="#">Link</a></li>--}%
                    <li class="divider"></li>
                    <li><a href="#"><i class="icon-comment"></i> Settings</a></li>
                    <li><a href="#"><i class="icon-share"></i> Logout</a></li>
                </ul>
            </div><!--/.well -->
        </div><!--/span-->

        <div class="span10">
        <!-- Secondary menu in one row (e.g., actions for current controller) -->
            <g:if test="${!layout_nosecondarymenu}">
                <div class="row">
                    <div class="span12">
                        <g:render template="/_menu/submenubar"/>
                    </div>
                </div>
            </g:if>
            <g:layoutBody/>
            <g:pageProperty name="page.body"/>
        </div>

    </div>

</div>

