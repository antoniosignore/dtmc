<%@ page import="com.netnumeri.server.finance.finpojo.Portfolio" %>



<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'description', 'error')} ">
    <label for="description" class="control-label"><g:message
            code="portfolio.description.label"
            default="Description"/></label>

    <div class="controls">
        <g:textField name="description" value="${portfolioInstance?.description}"/>
        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'description', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'firstDate', 'error')} ">
    <label for="firstDate" class="control-label"><g:message
            code="portfolio.firstDate.label"
            default="First Date"/></label>

    <div class="controls">
        <bs:datePicker name="firstDate" precision="day" value="${portfolioInstance?.firstDate}" default="none"
                       noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'firstDate', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'items', 'error')} ">
    <label for="items" class="control-label"><g:message
            code="portfolio.items.label"
            default="Items"/></label>

    <div class="controls">

        <ul class="one-to-many">
            <g:each in="${portfolioInstance?.items ?}" var="i">
                <li><g:link controller="portfolioEntry" action="show" id="${i.id}">${i?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="portfolioEntry" action="create"
                        params="['portfolio.id': portfolioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'portfolioEntry.label', default: 'PortfolioEntry')])}</g:link>
            </li>
        </ul>

        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'items', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'lastDate', 'error')} ">
    <label for="lastDate" class="control-label"><g:message
            code="portfolio.lastDate.label"
            default="Last Date"/></label>

    <div class="controls">
        <bs:datePicker name="lastDate" precision="day" value="${portfolioInstance?.lastDate}" default="none"
                       noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'lastDate', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'name', 'error')} ">
    <label for="name" class="control-label"><g:message
            code="portfolio.name.label"
            default="Name"/></label>

    <div class="controls">
        <g:textField name="name" value="${portfolioInstance?.name}"/>
        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'name', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'portfolioType', 'error')} ">
    <label for="portfolioType" class="control-label"><g:message
            code="portfolio.portfolioType.label"
            default="Portfolio Type"/></label>

    <div class="controls">
        <g:select name="portfolioType" from="${com.netnumeri.server.enums.PortfolioEnum?.values()}"
                  keys="${com.netnumeri.server.enums.PortfolioEnum.values()*.name()}"
                  value="${portfolioInstance?.portfolioType?.name()}" noSelection="['': '']"/>
        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'portfolioType', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'transactions', 'error')} ">
    <label for="transactions" class="control-label"><g:message
            code="portfolio.transactions.label"
            default="Transactions"/></label>

    <div class="controls">

        <ul class="one-to-many">
            <g:each in="${portfolioInstance?.transactions ?}" var="t">
                <li><g:link controller="transaction" action="show" id="${t.id}">${t?.encodeAsHTML()}</g:link></li>
            </g:each>
            <li class="add">
                <g:link controller="transaction" action="create"
                        params="['portfolio.id': portfolioInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'transaction.label', default: 'Transaction')])}</g:link>
            </li>
        </ul>

        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'transactions', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'user', 'error')} ">
    <label for="user" class="control-label"><g:message
            code="portfolio.user.label"
            default="User"/></label>

    <div class="controls">
        <g:select id="user" name="user.id" from="${com.dtmc.club.Member.list()}" optionKey="id"
                  value="${portfolioInstance?.user?.id}" class="many-to-one" noSelection="['null': '']"/>
        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'user', 'error')}</span>
    </div>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: portfolioInstance, field: 'wealth', 'error')} required">
    <label for="wealth" class="control-label"><g:message
            code="portfolio.wealth.label"
            default="Wealth"/><span class="required-indicator">*</span></label>

    <div class="controls">
        <g:field type="number" name="wealth" step="any" required="" value="${portfolioInstance.wealth}"/>
        <span class="help-inline">${hasErrors(bean: portfolioInstance, field: 'wealth', 'error')}</span>
    </div>
</div>

