<%@ include file="/WEB-INF/view/module/radiology/template/includeScripts.jsp"%>

<script type="text/javascript">
	var $j = jQuery.noConflict();
	
	$j(document).ready(function() {
	  
	 	$j("#reportType").change(function() {
	 	  	console.log("you are changing the value of report type to " + $j('#reportType').val());
	 		var reportType = $j('#reportType').val();
	 		if (reportType == "mrrt") {
	 		  $j('#templateIdSection').show();
	 		}
	 		else {
	 		  $j('#templateIdSection').hide();
	 		}
	 	});
	 
	 	console.log(Radiology.getRestRootEndpoint() + "/mrrtreporttemplate/");
	 	
	 	$j.getJSON(Radiology.getRestRootEndpoint() + "/mrrtreporttemplate/", function(result) {
	 		var options = $j("#templateId");
	 		
	 		$j.each(result, function() {
	 			options.append($j("<option />").val(this.templateId).text(this.dcTermsTitle));
	 		});
	 	});
	});
</script>
<br>
<div>
  <span class="boxHeader"> <b><spring:message code="radiology.radiologyReportTitle" /></b>
  </span>
  <c:choose>
    <c:when test="${radiologyReportNeedsToBeCreated}">
      <form:form method="post" modelAttribute="radiologyOrder" cssClass="box" id="claimReportForm">
      <spring:bind path="orderId">
      	<input type="hidden" name="orderId" value="${status.value}"/>
      </spring:bind>
      <table>
        <tr>
            <td><label for="reportType">Type</label></td>
            <td>
            <select name="reportType" id="reportType">
              <option value="">Choose a type</option>
              <option value="freeText">Free Text</option>
              <option value="mrrt">MRRT Report</option>
            </select></td>
        </tr>
        <tr id="templateIdSection" style="display: none">
          <td><label for="templates">Template Id</label></td>
          <td><select name="templateId" id="templateId">
          </select> </td>
        </tr>
        <tr>
           <td>
           </td>
           <td><input type="submit" value="<spring:message code="radiology.radiologyReportClaim"/>"/></td>
        </tr>
        </table>
      </form:form>
    </c:when>
    <c:otherwise>
      <form:form method="post" modelAttribute="radiologyReport" cssClass="box">
        <tr>
          <td><a
            href="${pageContext.request.contextPath}/module/radiology/radiologyReport.form?reportId=${radiologyReport.id}">
              <c:choose>
                <c:when test="${radiologyReport.status == 'DRAFT'}">
                  <spring:message code="radiology.radiologyReportResume" />
                </c:when>
                <c:otherwise>
                  <spring:message code="radiology.radiologyReportShow" />
                </c:otherwise>
              </c:choose>
          </a></td>
        </tr>
      </form:form>
    </c:otherwise>
  </c:choose>
</div>
