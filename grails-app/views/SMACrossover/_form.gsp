<%@ page import="com.netnumeri.server.finance.finpojo.SMACrossover" %>



<div class="control-group fieldcontain ${hasErrors(bean: SMACrossoverInstance, field: 'higherMA', 'error')} ">
	<label for="higherMA">
		<g:message code="SMACrossover.higherMA.label" default="Higher MA" />
		
	</label>
	<g:field name="higherMA" type="number" value="${SMACrossoverInstance.higherMA}"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: SMACrossoverInstance, field: 'lowerMA', 'error')} ">
	<label for="lowerMA">
		<g:message code="SMACrossover.lowerMA.label" default="Lower MA" />
		
	</label>
	<g:field name="lowerMA" type="number" value="${SMACrossoverInstance.lowerMA}"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: SMACrossoverInstance, field: 'member', 'error')} ">
	<label for="member">
		<g:message code="SMACrossover.member.label" default="Member" />
		
	</label>
	<g:select id="member" name="member.id" from="${com.dtmc.club.Member.list()}" optionKey="id" value="${SMACrossoverInstance?.member?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: SMACrossoverInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="SMACrossover.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${SMACrossoverInstance?.name}"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: SMACrossoverInstance, field: 'portfolio', 'error')} ">
	<label for="portfolio">
		<g:message code="SMACrossover.portfolio.label" default="Portfolio" />
		
	</label>
	<g:select id="portfolio" name="portfolio.id" from="${com.netnumeri.server.finance.finpojo.Portfolio.list()}" optionKey="id" value="${SMACrossoverInstance?.portfolio?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: SMACrossoverInstance, field: 'strategyEnum', 'error')} ">
	<label for="strategyEnum">
		<g:message code="SMACrossover.strategyEnum.label" default="Strategy Enum" />
		
	</label>
	<g:select name="strategyEnum" from="${com.netnumeri.server.enums.StrategyEnum?.values()}" keys="${com.netnumeri.server.enums.StrategyEnum.values()*.name()}" value="${SMACrossoverInstance?.strategyEnum?.name()}" noSelection="['': '']"/>
</div>

<div class="control-group fieldcontain ${hasErrors(bean: SMACrossoverInstance, field: 'when', 'error')} ">
	<label for="when">
		<g:message code="SMACrossover.when.label" default="When" />
		
	</label>
	<g:datePicker name="when" precision="day"  value="${SMACrossoverInstance?.when}" default="none" noSelection="['': '']" />
</div>

