<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title><g:layoutTitle default="Grails Mongo"/></title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
    <link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
    <link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
    <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    <r:require modules="bootstrap"/>
    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container-fluid">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <a class="brand" href="#" name="top">Brand Name</a>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li><a href="#"><i class="icon-home"></i> Home</a></li>
                    <li class="divider-vertical"></li>
                    <li class="active"><a href="#"><i class="icon-file"></i> Pages</a></li>
                    <li class="divider-vertical"></li>
                    <li><a href="#"><i class="icon-envelope"></i> Messages</a></li>
                    <li class="divider-vertical"></li>
                    <li><a href="#"><i class="icon-signal"></i> Stats</a></li>
                    <li class="divider-vertical"></li>
                    <li><a href="#"><i class="icon-lock"></i> Permissions</a></li>
                    <li class="divider-vertical"></li>
                </ul>
                <ul class="nav pull-right">
                    <li><a href="/signup">Sign Up</a></li>
                    <li class="divider-vertical"></li>
                    <li class="dropdown">
                        <a class="dropdown-toggle" href="#" data-toggle="dropdown">Sign In <strong
                                class="caret"></strong></a>

                        <div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
                            <form method="post" action="login" accept-charset="UTF-8">
                                <input style="margin-bottom: 15px;" type="text" placeholder="Username" id="username"
                                       name="username">
                                <input style="margin-bottom: 15px;" type="password" placeholder="Password" id="password"
                                       name="password">
                                <input style="float: left; margin-right: 10px;" type="checkbox" name="remember-me"
                                       id="remember-me" value="1">
                                <label class="string optional" for="user_remember_me">Remember me</label>
                                <input class="btn btn-primary btn-block" type="submit" id="sign-in" value="Sign In">
                                <label style="text-align:center;margin-top:5px">or</label>
                                <input class="btn btn-primary btn-block" type="button" id="sign-in-google"
                                       value="Sign In with Google">
                                <input class="btn btn-primary btn-block" type="button" id="sign-in-twitter"
                                       value="Sign In with Twitter">
                            </form>
                        </div>
                    </li>
                </ul>
            </div>
            <!--/.nav-collapse -->
        </div>
        <!--/.container-fluid -->
    </div>
    <!--/.navbar-inner -->
</div>
<!--/.navbar -->

<script>
    $(document).ready(function () {
        //Handles menu drop down
        $('.dropdown-menu').find('form').click(function (e) {
            e.stopPropagation();
        });
    });
</script>

<div class="sidebar-nav">
    <div class="well" style="width:300px; padding: 8px 0;">
        <ul class="nav nav-list">
            <li class="nav-header">Admin Menu</li>
            <li><a href="index"><i class="icon-home"></i> Dashboard</a></li>
            <li><a href="#"><i class="icon-envelope"></i> Messages <span class="badge badge-info">4</span></a></li>
            <li><a href="#"><i class="icon-comment"></i> Comments <span class="badge badge-info">10</span></a></li>
            <li class="active"><a href="#"><i class="icon-user"></i> Members</a></li>
            <li class="divider"></li>
            <li><a href="#"><i class="icon-comment"></i> Settings</a></li>
            <li><a href="#"><i class="icon-share"></i> Logout</a></li>
        </ul>
    </div>
</div>

<div class="container-fluid">

    <div class="navbar navbar-static-top">
        <div class="navbar-inner">
            <div class="container">
                <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <g:link class="brand" uri="/">Don't touch my cheese</g:link>
                <div class="nav-collapse">
                    <ul class="nav pull-right">
                        <sec:ifLoggedIn>
                            <li>
                                <g:link controller="logout" action="index">Logout</g:link>
                            </li>
                        </sec:ifLoggedIn>
                    </ul>
                </div>
            </div>
        </div>
    </div>


    <table id="MyGrid" class="table table-bordered datagrid">
        <thead>
        <tr>
            <th>
                <span class="datagrid-header-title">Geographic Data Sample</span>

                <div class="datagrid-header-left">
                    <div class="input-append search datagrid-search">
                        <input type="text" class="input-medium" placeholder="Search">
                        <button type="button" class="btn"><i class="icon-search"></i></button>
                    </div>
                </div>

                <div class="datagrid-header-right">
                    <div class="select filter" data-resize="auto">
                        <button type="button" data-toggle="dropdown" class="btn dropdown-toggle">
                            <span class="dropdown-label"></span>
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu">
                            <li data-value="all" data-selected="true"><a href="#">All</a></li>
                            <li data-value="lt5m"><a href="#">Population < 5M</a></li>
                            <li data-value="gte5m"><a href="#">Population >= 5M</a></li>
                        </ul>
                    </div>
                </div>
            </th>
        </tr>
        </thead>
        <tfoot>
        <tr>
            <th>
                <div class="datagrid-footer-left" style="display:none;">
                    <div class="grid-controls">
                        <span>
                            <span class="grid-start"></span> -
                            <span class="grid-end"></span> of
                            <span class="grid-count"></span>
                        </span>

                        <div class="select grid-pagesize" data-resize="auto">
                            <button type="button" data-toggle="dropdown" class="btn dropdown-toggle">
                                <span class="dropdown-label"></span>
                                <span class="caret"></span>
                            </button>
                            <ul class="dropdown-menu">
                                <li data-value="5" data-selected="true"><a href="#">5</a></li>
                                <li data-value="10"><a href="#">10</a></li>
                                <li data-value="20"><a href="#">20</a></li>
                                <li data-value="50"><a href="#">50</a></li>
                                <li data-value="100"><a href="#">100</a></li>
                            </ul>
                        </div>
                        <span>Per Page</span>
                    </div>
                </div>

                <div class="datagrid-footer-right" style="display:none;">
                    <div class="grid-pager">
                        <button type="button" class="btn grid-prevpage"><i class="icon-chevron-left"></i></button>
                        <span>Page</span>

                        <div class="input-append dropdown combobox">
                            <input class="span1" type="text">
                            <button type="button" class="btn" data-toggle="dropdown"><i class="caret"></i></button>
                            <ul class="dropdown-menu"></ul>
                        </div>
                        <span>of <span class="grid-pages"></span></span>
                        <button type="button" class="btn grid-nextpage"><i class="icon-chevron-right"></i></button>
                    </div>
                </div>
            </th>
        </tr>
        </tfoot>
    </table>


    <script>
        $('#MyGrid').datagrid({ dataSource: dataSource, stretchHeight: true })
    </script>


    <g:layoutBody/>
    <hr>

    <footer>
        <p>&copy; Dont;Touch my cheese 2013</p>
    </footer>

</div>

<r:layoutResources/>

</body>
</html>