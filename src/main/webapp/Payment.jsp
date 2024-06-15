<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	/* Basic CSS Reset */
body, html {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
}

/* Container Styling */
.container1 {
    max-width: 400px;
    margin: 50px auto;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    background-color: #f9f9f9;
    box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
}

/* Header Styling */
h2 {
    text-align: center;
    margin-bottom: 20px;
}

/* Form Group Styling */
.form-group {
    margin-bottom: 15px;
}

/* Label Styling */
label {
    display: block;
    font-weight: bold;
    margin-bottom: 5px;
}

/* Input Field Styling */
input[type="text"],
input[type="email"],
input[type="number"],
input[type="date"],
input[type="text"] {
    width: 100%;
    padding: 10px;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
}

/* Submit Button Styling */
button.submit2 {
    background-color: #4CAF50;
    color: white;
    padding: 12px 20px;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    width: 100%;
}

button.submit2:hover {
    background-color: #45a049;
}
	
</style>
</head>
<body>

	<div class="body1">
		<div class="container1">
			<h2>
				<c:if test="${paymentInfo != null}">
					<span>Edit</span> Payment Information
        </c:if>
				<c:if test="${paymentInfo == null}">
					<span>Add</span> Payment Information
        </c:if>
			</h2>
			<div class="form-container">
            <img src="css/image/onlineCard.png" alt="Cards Image">
			<form id="paymentForm"
				action="<c:if test='${paymentInfo != null}'>payment_update</c:if><c:if test='${paymentInfo == null}'>payment_insert</c:if>"
				method="post">

				<c:if test="${paymentInfo != null}">
					<input type="hidden" name="id"
						value="<c:out value='${paymentInfo.id}' />" />
				</c:if>
				<div class="form-group">
					<label for="card-holder-name"><strong>Card Holder Name:</strong></label>
					<input type="text" id="card-holder-name" name="card-holder-name" value="<c:out value='${paymentInfo.cardHolderName}' />" placeholder="Card Holder Name" required>
				</div>
				
				<div class="form-group">
					<label for="email-address"><strong>Email Address:</strong></label> <input
						type="email" id="email-address" name="email-address"
						value="<c:out value='${paymentInfo.emailAddress}' />"
						placeholder="Email Address" required>
				</div>
				<div class="form-group">
					<label for="amount"><strong>Amount</strong></label> <input type="number"
						id="amount" name="amount"
						value="<c:out value='${paymentInfo.amount}' />"
						placeholder="Amount" required>
				</div>
				<div class="form-group">
					<label for="card-number"><strong>Card Number:</strong></label>
					<input type="text" id="card-number" name="card-number" value="<c:out value='${paymentInfo.cardNumber}' />" placeholder="Enter Card Number" maxlength="16" pattern="[0-9]{0,16}" title="Numbers only, up to 16 digits" required oninput="validateCardNumber(this);">
					<div id="card-number-error" style="color: red; display: none;">Please enter a valid card number (up to 16 digits).</div>
				</div>
				<div class="form-group">
					<label for="expiry-date"><strong>Expiry Date:</strong></label> <input type="date"
						id="expiry-date" name="expiry-date"
						value="<c:out value='${paymentInfo.expiryDate}' />"
						placeholder="MM/YY" min="<%= (new java.text.SimpleDateFormat("yyyy-MM-dd")).format(new java.util.Date()) %>" required>
				</div>
				<div class="form-group">
					<label for="cvv"><strong>CVV:</strong></label> 
					<input type="text" id="cvv" name="cvv" value="<c:out value='${paymentInfo.cvv}' />" placeholder="CVV" maxlength="3" pattern="[0-9]*" title="Numbers only" required>
				</div>
				
				<div id="fillInMessage" style="color: red;"></div>
				<button type="submit" class="submit2">
					<c:if test="${paymentInfo != null}">Update</c:if>
					<c:if test="${paymentInfo == null}">Add</c:if>
				</button>
			</form>
			</div>
		</div>
	</div>

</body>
</html>