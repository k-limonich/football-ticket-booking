let ticketCart = document.getElementById('ticket-cart');

function onSeatButtonClick(seat) {
    if (seat.classList.contains('free')) {
        changeSeatStatus(seat, 'free', 'selected')
        addSeatToCart(seat);
    } else if (seat.classList.contains('selected')) {
        changeSeatStatus(seat, 'selected', 'free');
        removeSeatFromCart(seat);
    }
    updateSelectedSeatsInput();
    showOrHideToPaymentButton();
}

function changeSeatStatus(seat, oldStatus, newStatus) {
    seat.classList.remove(oldStatus);
    seat.classList.add(newStatus);
}

function addSeatToCart(seat) {
    let ticketCartItem = document.createElement("li");
    ticketCartItem.classList.add('list-group-item');
    ticketCartItem.id = 'ticket-seat-' + seat.id;
    let ticketInfo = document.createTextNode(seat.name);
    ticketCartItem.appendChild(ticketInfo);
    ticketCart.appendChild(ticketCartItem);
}

function removeSeatFromCart(seat) {
    let ticketCartItem = document.getElementById('ticket-seat-' + seat.id);
    ticketCart.removeChild(ticketCartItem);
}

function updateSelectedSeatsInput() {
    let selectedSeatsInput = document.getElementById('selected-seats-input');
    let selectedSeats = document.getElementsByClassName('selected');
    selectedSeatsInput.value = Array.from(selectedSeats, seat => seat.id);
}

function showOrHideToPaymentButton() {
    let toPaymentButton = document.getElementById('to-payment-btn');
    let buttonIsHidden = toPaymentButton.style.display === 'none';
    if (buttonIsHidden && ticketCart.children.length > 0) {
        toPaymentButton.style.display = 'block';
    } else if (!buttonIsHidden && ticketCart.children.length === 0) {
        toPaymentButton.style.display = 'none';
    }
}