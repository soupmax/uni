document.addEventListener('DOMContentLoaded', () => {
  const startScreen = document.getElementById('pingStart');
  const gameScreen = document.getElementById('pingGame');
  const startButton = document.getElementById('start-button');
  const quitButton = document.getElementById('quit-button');
  const canvas = document.getElementById('game-canvas');
  const ctx = canvas.getContext('2d');
  const scoreDisplay = document.getElementById('score');

  if (!startScreen || !gameScreen || !startButton || !quitButton || !canvas)
    return;

  let gameInterval;
  let playerScore = 0;
  let enemyScore = 0;

  const paddleHeight = 100;
  const paddleWidth = 10;

  const player = { x: 20, y: 250, width: paddleWidth, height: paddleHeight };
  const enemy = { x: 770, y: 250, width: paddleWidth, height: paddleHeight };
  const ball = { x: 400, y: 300, dx: 3, dy: 3, size: 10 };

  function resetBall() {
    ball.x = 400;
    ball.y = 300;
    ball.dx = -ball.dx;
    ball.dy = Math.random() > 0.5 ? 3 : -3;
  }

  function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = 'white';

    ctx.fillRect(player.x, player.y, player.width, player.height);
    ctx.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
    ctx.fillRect(ball.x, ball.y, ball.size, ball.size);

    ball.x += ball.dx;
    ball.y += ball.dy;

    if (ball.y <= 0 || ball.y + ball.size >= canvas.height) ball.dy *= -1;

    if (
      ball.x <= player.x + player.width &&
      ball.y + ball.size >= player.y &&
      ball.y <= player.y + player.height
    ) {
      ball.dx *= -1;
    }

    if (
      ball.x + ball.size >= enemy.x &&
      ball.y + ball.size >= enemy.y &&
      ball.y <= enemy.y + enemy.height
    ) {
      ball.dx *= -1;
    }

    if (ball.x < 0) {
      enemyScore++;
      resetBall();
    } else if (ball.x > canvas.width) {
      playerScore++;
      resetBall();
    }

    if (enemy.y + enemy.height / 2 < ball.y) {
      enemy.y += 3;
    } else {
      enemy.y -= 3;
    }

    canvas.onmousemove = (e) => {
      const rect = canvas.getBoundingClientRect();

      // Zielposition berechnen (Mitte der Maus relativ zum Canvas)
      let targetY = e.clientY - rect.top - player.height / 2;

      // Begrenzung, damit Paddle nicht aus dem Canvas rutscht
      player.y = Math.max(0, Math.min(canvas.height - player.height, targetY));
    };

    scoreDisplay.textContent = `Spieler: ${playerScore} | Gegner: ${enemyScore}`;
  }

  startButton.addEventListener('click', () => {
    startScreen.style.display = 'none';
    gameScreen.style.display = 'block';
    resetBall();
    gameInterval = setInterval(draw, 1000 / 60);
  });

  quitButton.addEventListener('click', () => {
    clearInterval(gameInterval);
    gameScreen.style.display = 'none';
    startScreen.style.display = 'block';
  });
});
