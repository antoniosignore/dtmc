<%@ page import="com.netnumeri.server.finance.finpojo.asset.Stock" %>



<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'description', 'error')} ">
    <label for="description">
        <g:message code="stock.description.label" default="Description"/>
        
    </label>
    <g:textField name="description" value="${stockInstance?.description}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: stockInstance, field: 'name', 'error')} ">
    <label for="name">
        <g:message code="stock.name.label" default="Name"/>
        
    </label>
    <g:textField name="name" value="${stockInstance?.name}"/>
</div>

