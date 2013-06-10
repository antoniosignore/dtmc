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
    <g:layoutHead/>
    <r:layoutResources/>
</head>

<body>

<div class="navbar">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <i class="icon-bar"></i>
                <i class="icon-bar"></i>
                <i class="icon-bar"></i>
            </a>

            <a class="brand" href="#">Brand</a>

            <div class="btn-group pull-left">
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    Portfolio
                    <i class="caret"></i>
                </a>
                <ul class="dropdown-menu no-collapse">
                    <li>
                        <a href="create">Create Portfolio</a>
                    </li>
                    <li class="active">
                        <a href="list">Manage Portfolios</a>
                    </li>
                    %{--<li class="divider"></li>--}%
                    %{--<li>--}%
                    %{--<a href="#">Link</a>--}%
                    %{--</li>--}%
                </ul>
            </div>

            <div class="btn-group pull-right">
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    Stock
                    <i class="caret"></i>
                </a>
                <ul class="dropdown-menu no-collapse">
                    <li>
                        <a href="create">Create Stock</a>
                    </li>
                    <li class="active">
                        <a href="list">Manage Stocks</a>
                    </li>
                </ul>
            </div>

            %{--<div class="nav-collapse">--}%
            %{--<ul class="nav">--}%
            %{--<li>--}%
            %{--<a href="#">Link</a>--}%
            %{--</li>--}%
            %{--<li class="active">--}%
            %{--<a href="#">Link</a>--}%
            %{--</li>--}%
            %{--<li class="dropdown">--}%
            %{--<a class="dropdown-toggle" data-toggle="dropdown" href="#">--}%
            %{--Dropdown--}%
            %{--<i class="caret"></i>--}%
            %{--</a>--}%
            %{--<ul class="dropdown-menu">--}%
            %{--<li>--}%
            %{--<a href="#">Link</a>--}%
            %{--</li>--}%
            %{--<li class="active">--}%
            %{--<a href="#">Link</a>--}%
            %{--</li>--}%
            %{--<li class="divider"></li>--}%
            %{--<li>--}%
            %{--<a href="#">Link</a>--}%
            %{--</li>--}%
            %{--</ul>--}%
            %{--</li>--}%
            %{--<li class="divider-vertical"></li>--}%
            %{--<li>--}%
            %{--<a href="#">Link</a>--}%
            %{--</li>--}%
            %{--</ul>--}%
            %{--</div>--}%
        </div>
    </div>
</div>


<script>
    $(document).ready(function () {
        //Handles menu drop down
        $('.dropdown-menu').find('form').click(function (e) {
            e.stopPropagation();
        });
    });
</script>

<div class="container-fluid">

    <g:layoutBody/>


    <div>

    </div>


    <footer>
        <p>&copy; Don't Touch my cheese 2013</p>
    </footer>

</div>

<r:layoutResources/>

</body>
</html>