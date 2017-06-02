<%@ page import="com.gcit.lms.service.AdminBorrower"%>
<%@ page import="com.gcit.lms.service.AdminLibrarian"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%
	AdminBorrower service = new AdminBorrower();
	AdminLibrarian service1 = new AdminLibrarian();
	List<Branch> branches = service1.readAllBranches();
	Long cardNo = Long.parseLong((String) request.getParameter("cardNo"));
%>
<center>
	<%@include file="index.htm"%>
	<div>
		<div class="jumbotron">
			<h2>Pick the branch you want to check out books from!</h2>
		</div>
</center>

<div class="page-header"></div>

<%
	if (request.getAttribute("checkMessage") != null) {
%>
<div class="alert alert-success" role="alert">
  <strong>Happy reading!</strong> The book has been checked out!
</div>
<% } %>

<div class="row">
	<div class="col-md-6">
		<center>
			<div style="margin-left: 150px">
				<table class="table gcit">
					<thead>
						<tr>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<%
							int id = 1;
							for (Branch b : branches) {
						%>
						<tr>
							<td>
								<%
									out.println(b.getBranchName());
								%>
							</td>
							<td><button type="button" class="btn btn-primary"
									data-toggle="modal"
									data-target="#checkOutBooksFromBranchModal<%=id%> "
									role="button"
									href="checkoutfrombranch.jsp?branchId=<%=b.getBranchId()%>&cardNo=<%=cardNo%>">
									Check Out</button></td>
						</tr>
						<%
							id++;
							}
						%>
					</tbody>
				</table>
		</center>
	</div>
</div>
</div>



<%
	int id1 = 1;
	for (Branch b : branches) {
%>
<div class="modal fade bs-example-modal-sm"
	id="checkOutBooksFromBranchModal<%=id1%>" tabindex="-1" role="dialog"
	aria-labelledby="myLargeModalLabel">
	<div class="modal-dialog modal-lg" role="document">
		<div class="modal-content">...</div>
	</div>
</div>
<%
	id1++;
	}
%>
<<center>
<p>
	<a href="borrower.jsp" class="btn btn-info btn-lg"> <span
		class="glyphicon glyphicon-log-out"></span> Log out
	</a>
</p>
</center>