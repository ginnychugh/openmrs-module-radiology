<script type="text/javascript">
	var $j = jQuery.noConflict();
	
	$j(document).ready(function() {
		
		$j('#templateSelectSection').hide();
		
		$j("#reportType").change(function() {
	 		var reportType = $j('#reportType').val();
	 		
	 		if (reportType == "mrrt") {
	 		  $j('#templateSelectSection').show();
	 		}
			else {
				$j('#templateSelectSection').hide();
			}
		});
	
	});
</script>

<br>
<div>
  <span class="boxHeader"> <b><spring:message code="radiology.radiologyReportTitle" /></b>
  </span>
  <c:choose>
    <c:when test="${radiologyReportNeedsToBeCreated}">
      <form:form method="get" modelAttribute="radiologyOrder" cssClass="box" action="${pageContext.request.contextPath}/module/radiology/radiologyReport.form">
      	<spring:bind path="orderId">
        	<input type="hidden" name="orderId" id="orderId" value="${status.value}"/>
        </spring:bind>
        <table>
        	<tr>
        		<td><label for="reportType"><spring:message code="radiology.reportType"/></label></td>
        		<td>
        			<select name="reportType" id="reportType">
        				<option value=""><spring:message code="radiology.report.chooseType"/></option>
        				<option value="freeText"><spring:message code="radiology.report.freeText"/></option>
        				<option value="mrrt"><spring:message code="radiology.report.mrrt" /></option>
        			</select>
        		</td>
        	</tr>
        	<tr id="templateSelectSection">
        		<td><label for="templateId"><spring:message code="radiology.report.mrrtTemplate"/></label></td>
        		<td>
        			<select id="templateId" name="templateId">
        				<option value=""><spring:message code="radiology.report.chooseTemplate"/></option>
        			</select>
        		</td>
        	</tr>
        	<tr>
        		<td></td>
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
