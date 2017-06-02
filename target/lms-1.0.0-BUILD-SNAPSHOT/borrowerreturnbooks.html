<%@ page import="com.gcit.lms.service.AdminBorrower"%>
<%@ page import="java.util.List"%>
<%@ page import="com.gcit.lms.entity.*"%>
<%@ page import="com.gcit.lms.dao.*"%>
<%
	Borrower borrower = new Borrower();
	AdminBorrower service = new AdminBorrower();
	Long cardNo = Long.parseLong(request.getParameter("cardNo"));
	borrower = service.returnBorrowerByCardNo(cardNo).get(0);
%>

<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-label="Close">
		<span aria-hidden="true">&times;</span>
	</button>
	<h4 class="modal-title">
		Return books,
		<%=borrower.getBorrowerName()%>!
	</h4>
</div>
<form action="returnBook" method="post">
	<div class="modal-body">
		<div class="form-group">
			<label for="sel1">Select list:</label> <select class="form-control"
				id="sel1" name = "book">
				<% for(Book b : borrower.getBooksDue()) { %>
					<option><%= b.getBookName()%> </option>
					<% } %>
			</select>
		</div>

	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
		<button type="submit" class="btn btn-primary">Return book</button>
	</div>
	<input type = "hidden" name = "cardNo" value = "<%= borrower.getCardNo() %>"> <br/>
	
</form>
<div>