<header id="Header">
    <div class="container-fluid">
        <div class="col-md-10 column">
            <ul class="nav nav-pills">
                <li class="${'portfolio'.equals(params.controller) ? 'active' : ''}">
                    <a href="/dtmc/portfolio">Portfolio</a>
                </li>
                <li class="${'stock'.equals(params.controller) ? 'active' : ''}">
                    <a href="/dtmc/stock">Stock</a>
                </li>
            </ul>
        </div>
    </div>
</header>

