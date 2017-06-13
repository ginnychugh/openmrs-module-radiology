<%@ include file="/WEB-INF/template/include.jsp"%>
<c:set var="DO_NOT_INCLUDE_JQUERY" value="true" />
<%@ include file="/WEB-INF/template/header.jsp"%>
<c:set var="INCLUDE_TIME_ADJUSTMENT" value="true" />
<%@ include file="/WEB-INF/view/module/radiology/template/includeScripts.jsp"%>
<openmrs:htmlInclude file="/moduleResources/radiology/vendor/tinymce/tinymce.min.js" />
<openmrs:htmlInclude file="/moduleResources/radiology/vendor/font-awesome/css/font-awesome.min.css" />

<script type="text/javascript">
    var $j = jQuery.noConflict();

    function showVoidRadiologyReportDialog() {
        var dialogDiv = $j("<div></div>")
            .html(
                '<spring:message code="radiology.report.form.void.dialog.message"/>');
        dialogDiv
            .dialog({
                resizable: false,
                width: 'auto',
                height: 'auto',
                title: '<spring:message code="radiology.report.form.void.dialog.title"/>',
                modal: true,
                buttons: {
                    '<spring:message code="radiology.report.form.void.dialog.button.ok"/>': function() {
                        $j(this).dialog("close");
                        submitVoidRadiologyReport();
                    },
                    '<spring:message code="radiology.report.form.void.dialog.button.cancel"/>': function() {
                        $j(this).dialog("close");
                    }
                }
            });
    }

    function submitVoidRadiologyReport() {
        var voidRadiologyReport = $j("<input>").attr("type", "hidden").attr("name",
            "voidRadiologyReport").val('<spring:message code="general.void"/>');
        $j("#voidRadiologyReportForm").append(voidRadiologyReport);
        $j("#voidRadiologyReportForm").submit()
    }

    $j(document).ready(function() {

        $j('#completeRadiologyReportButton').click(function() {
            $j('input:text, input:hidden, input:password').each(function() {
                var v=this.value;
                $j(this).attr("magicmagic_value",v).removeAttr("value").val(v);
            });
            $j('input:checkbox,input:radio').each(function() {
                var v=this.checked;
                if(v) $j(this).attr("magicmagic_checked","checked");
                $j(this).removeAttr("checked");
                if(v) this.checked=true;
            });
            $j('select option').each(function() {
                var v=this.selected;
                if(v) $j(this).attr("magicmagic_selected","selected");
                $j(this).removeAttr("selected");
                if(v) this.selected=true;
            });
            $j('textarea').each(function() {
                $j(this).html(this.value);
            });

            var magic=$j('#mrrtReportBody').html().replace(/magicmagic_/g,"");

            $j('[magicmagic_value]').removeAttr('magicmagic_value');
            $j('[magicmagic_checked]').attr("checked","checked").removeAttr('magicmagic_checked');
            $j('[magicmagic_selected]').attr("selected","selected").removeAttr('magicmagic_selected');
            $j('#reportBody').val(magic);
            console.log($j('#reportBody').val());
            $j('#radiologyReportFormId').submit();
        });
    });

</script>

<openmrs:hasPrivilege privilege="View Patients">
    <openmrs:portlet url="patientHeader" id="patientDashboardHeader" patientId="${radiologyOrder.patient.patientId}" parameters="showPatientDashboardLink=true" />
    <br>
</openmrs:hasPrivilege>
<openmrs:portlet url="radiologyOrderDetails" moduleId="radiology"
                 parameters="orderUuid=${radiologyOrder.uuid}|withAccordion=true" />
<br>
<spring:hasBindErrors name="radiologyReport">
    <div class="error">
        <spring:message code="fix.error" />
    </div>
    <br>
</spring:hasBindErrors>
<c:if test="${radiologyReport.voided}">
    <div class="retiredMessage">
        <div>
            <spring:message code="general.voided" />
        </div>
    </div>
</c:if>
<span class="boxHeader"> <b><spring:message code="radiology.report.form.boxheader" /></b>
</span>
<form:form id="radiologyReportFormId" modelAttribute="radiologyReport" method="post">
    <div class="box">
        <table>
            <tr>
                <td><spring:message code="radiology.report.form.report.id" /></td>
                <td>${radiologyReport.id}</td>
                <form:hidden path="id" />
            </tr>
            <tr>
                    <%-- following properties are bound to the form as hidden since they should be or since we show them only in a readonly manner. --%>
                    <%-- if you delete for example the dateCreated it will change on every update of the RadiologyReport  --%>
                <form:hidden path="radiologyOrder" />
                <form:hidden path="uuid" />
                <form:hidden path="date" />
                <form:hidden path="status" />
                <form:hidden path="creator" />
                <form:hidden path="dateCreated" />
                <form:hidden path="voided" />
                <form:hidden path="voidedBy" />
                <form:hidden path="dateVoided" />
                    <%-- dateChanged and changedBy do not need to be bound  --%>
            </tr>
            <tr>
                <td><spring:message code="radiology.report.form.report.status" /></td>
                <td><spring:message code="radiology.report.status.${radiologyReport.status}" text="${radiologyReport.status}" />
                    <c:choose>
                        <c:when test="${radiologyReport.status == 'COMPLETED'}">
                            <i class="fa fa-check-circle fa-lg" />
                        </c:when>
                        <c:when test="${radiologyReport.status == 'DRAFT'}">
                            <c:choose>
                                <c:when test="${radiologyReport.voided}">
                                    <i class="fa fa-times-circle fa-lg" />
                                </c:when>
                                <c:otherwise>
                                    <i class="fa fa-circle fa-lg" />
                                </c:otherwise>
                            </c:choose>
                        </c:when>
                    </c:choose></td>
                </td>
            </tr>
            <c:if test="${radiologyReport.status == 'COMPLETED'}">
                <tr>
                    <td><spring:message code="radiology.report.form.report.date" /></td>
                    <td id="reportDateId"><spring:bind path="date">${status.value}</spring:bind></td>
                </tr>
            </c:if>
            <form:hidden path="body" id="reportBody"/>
            <tr>
                <td><spring:message code="radiology.report.form.report.principalResultsInterpreter" />
                    <c:choose>
                    <c:when test="${radiologyReport.voided || not empty radiologyReport.principalResultsInterpreter.id}">
                </td><td>
                    ${radiologyReport.principalResultsInterpreter.name}
                <form:hidden path="principalResultsInterpreter" />
                </c:when>
                <c:otherwise>
                <span class="required">*</span></td><td>
                <spring:bind path="principalResultsInterpreter">
                    <openmrs:fieldGen type="org.openmrs.Provider" formFieldName="${status.expression}"
                                      val="${status.editor.value}" />
                </spring:bind>
                <form:errors path="principalResultsInterpreter" cssClass="error" />
                </c:otherwise>
                </c:choose></td>
            </tr>
            <tr>
                <td><spring:message code="general.createdBy" /></td>
                <td><spring:bind path="creator.personName">
                    ${status.value}
                </spring:bind> - <span class="datetime"><spring:bind path="dateCreated">
                    ${status.value}
                </spring:bind></span></td>
            </tr>
            <c:if test="${radiologyReport.voided}">
                <tr>
                    <td><spring:message code="general.voidedBy" /></td>
                    <td><spring:bind path="voidedBy.personName">
                        ${status.value}
                    </spring:bind> - <span class="datetime"><spring:bind path="dateVoided">
                        ${status.value}
                    </spring:bind></span></td>
                </tr>
                <tr>
                    <td><spring:message code="general.voidReason" /></td>
                    <td><spring:bind path="voidReason">${status.value}</spring:bind></td>
                </tr>
            </c:if>
        </table>
    </div>
</form:form>
<br>

<c:if test="${radiologyReport.status != 'COMPLETED'}">
<span class="boxHeader"> <b><spring:message code="radiology.report.form.reportContent.boxheader" /></b>
</span>
    <div class="box">
        <div id="mrrtReportBody">
                ${mrrtReportTemplateBody}
        </div>
        <div>
            <c:if test="${(radiologyReport.status == 'DRAFT') && (not radiologyReport.voided)}">
                <input type="button" value="<spring:message code="radiology.report.form.button.complete"/>"
                       name="completeRadiologyReport" id="completeRadiologyReportButton"/>
            </c:if>
        </div>
    </div>
</c:if>

<c:if test="${(radiologyReport.status == 'DRAFT') && (not radiologyReport.voided)}">
    </br>
    <form:form method="post" id="voidRadiologyReportForm" modelAttribute="voidRadiologyReportRequest" cssClass="box">
        <table>
            <td><spring:message code="general.voidReason" /><span class="required">*</span></td>
            <td><form:textarea path="voidReason" /><form:errors path="voidReason" cssClass="error" />
            </td>
            </tr>
        </table>
        <input type="button" value="<spring:message code="general.void"/>" id="voidRadiologyReportButtonId" />
    </form:form>
</c:if>
<%@ include file="/WEB-INF/template/footer.jsp"%>
