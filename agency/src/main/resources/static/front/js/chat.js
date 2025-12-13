// Chat Widget Variables
let stompClient = null;
const chatWidget = document.getElementById('chatWidget');
const chatToggleBtn = document.getElementById('chatToggleBtn');
const chatWindow = document.getElementById('chatWindow');
const chatCloseBtn = document.getElementById('chatCloseBtn');
const chatMessages = document.getElementById('chatMessages');
const chatInput = document.getElementById('chatInput');
const chatSendBtn = document.getElementById('chatSendBtn');

// User info (you can get this from your authentication system)
const currentUserId = 'visitor_' + Math.random().toString(36).substr(2, 9);
const currentUserName = 'Visitor';
const supportUserId = 'support';

// Toggle chat window
chatToggleBtn.addEventListener('click', function() {
    if (chatWindow.style.display === 'none') {
        chatWindow.style.display = 'flex';
        chatToggleBtn.style.transform = 'scale(0.9)';
        if (!stompClient) {
            connectWebSocket();
        }
    } else {
        chatWindow.style.display = 'none';
        chatToggleBtn.style.transform = 'scale(1)';
    }
});

chatCloseBtn.addEventListener('click', function() {
    chatWindow.style.display = 'none';
    chatToggleBtn.style.transform = 'scale(1)';
});

// Connect to WebSocket
function connectWebSocket() {
    const socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);

    stompClient.connect({}, function(frame) {
        console.log('Connected to chat: ' + frame);

        // Subscribe to receive messages
        stompClient.subscribe('/user/' + currentUserId + '/queue/messages', function(message) {
            console.log('Received message:', message.body);
            const chatMessage = JSON.parse(message.body);
            displayMessage(chatMessage.content, false);
        });

        // Subscribe to notifications
        stompClient.subscribe('/user/' + currentUserId + '/queue/notifications', function(notification) {
            console.log('Received notification:', notification.body);
        });

        // Send welcome message
        displayMessage('Hello! How can we help you today?', false);
    }, function(error) {
        console.log('WebSocket connection error: ' + error);
        setTimeout(connectWebSocket, 5000); // Reconnect after 5 seconds
    });
}

// Send message
function sendMessage() {
    const messageContent = chatInput.value.trim();

    if (messageContent && stompClient) {
        const chatMessage = {
            senderId: currentUserId,
            senderName: currentUserName,
            recipientId: supportUserId,
            content: messageContent,
            timestamp: new Date()
        };

        stompClient.send("/app/chat", {}, JSON.stringify(chatMessage));

        // Display sent message
        displayMessage(messageContent, true);

        // Clear input
        chatInput.value = '';
    }
}

// Display message in chat
function displayMessage(content, isSent) {
    const messageDiv = document.createElement('div');
    messageDiv.style.marginBottom = '15px';
    messageDiv.style.display = 'flex';
    messageDiv.style.justifyContent = isSent ? 'flex-end' : 'flex-start';

    const messageBubble = document.createElement('div');
    messageBubble.textContent = content;
    messageBubble.style.maxWidth = '70%';
    messageBubble.style.padding = '10px 15px';
    messageBubble.style.borderRadius = '18px';
    messageBubble.style.fontSize = '14px';
    messageBubble.style.wordWrap = 'break-word';

    if (isSent) {
        messageBubble.style.background = 'linear-gradient(135deg, #667eea 0%, #764ba2 100%)';
        messageBubble.style.color = 'white';
    } else {
        messageBubble.style.background = 'white';
        messageBubble.style.color = '#333';
        messageBubble.style.border = '1px solid #e0e0e0';
    }

    messageDiv.appendChild(messageBubble);
    chatMessages.appendChild(messageDiv);

    // Scroll to bottom
    chatMessages.scrollTop = chatMessages.scrollHeight;
}

// Event listeners
chatSendBtn.addEventListener('click', sendMessage);
chatInput.addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        sendMessage();
    }
});

// Hover effect for chat button
chatToggleBtn.addEventListener('mouseenter', function() {
    this.style.transform = 'scale(1.1)';
});

chatToggleBtn.addEventListener('mouseleave', function() {
    if (chatWindow.style.display === 'none') {
        this.style.transform = 'scale(1)';
    }
});
