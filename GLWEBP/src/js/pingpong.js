/**
 * Initialisiert das Ping-Pong-Spiel, sobald das DOM geladen ist.
 */
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

  /**
   * Setzt die Ballposition zurück zur Mitte und wählt zufällig die Y-Richtung.
   * Die X-Richtung wird invertiert.
   *
   * @returns {void}
   */
  function resetBall() {
    ball.x = 400;
    ball.y = 300;
    const angle = Math.random() > 0.5 ? 1 : -1;
    ball.dx = ball.dx > 0 ? 3 : -3;
    ball.dy = angle * 3;
  }

  /**
   * Bewegt das Paddle des Spielers vertikal entsprechend der Mausposition.
   *
   * @param {MouseEvent} e - Mausbewegung im Canvas
   * @returns {void}
   */
  function handleMouseMove(e) {
    const rect = canvas.getBoundingClientRect();
    let targetY = e.clientY - rect.top - player.height / 2;
    player.y = Math.max(0, Math.min(canvas.height - player.height, targetY));
  }

  /**
   * Zeichnet das Spielfeld, bewegt den Ball, prüft auf Kollisionen und aktualisiert den Punktestand.
   *
   * @returns {void}
   */
  function draw() {
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    ctx.fillStyle = 'white';

    // Spielfläche zeichnen
    ctx.fillRect(player.x, player.y, player.width, player.height);
    ctx.fillRect(enemy.x, enemy.y, enemy.width, enemy.height);
    ctx.fillRect(ball.x, ball.y, ball.size, ball.size);

    // Ballbewegung
    ball.x += ball.dx;
    ball.y += ball.dy;

    // Wände oben/unten
    if (ball.y <= 0 || ball.y + ball.size >= canvas.height) ball.dy *= -1;

    // Kollision mit Spieler
    if (
      ball.x <= player.x + player.width &&
      ball.y + ball.size >= player.y &&
      ball.y <= player.y + player.height
    ) {
      ball.dx *= -1;
    }

    // Kollision mit Gegner
    if (
      ball.x + ball.size >= enemy.x &&
      ball.y + ball.size >= enemy.y &&
      ball.y <= enemy.y + enemy.height
    ) {
      ball.dx *= -1;
    }

    // Punktestand aktualisieren
    if (ball.x < 0) {
      enemyScore++;
      resetBall();
    } else if (ball.x > canvas.width) {
      playerScore++;
      resetBall();
    }

    // Gegnerbewegung (folgt Ball)
    if (enemy.y + enemy.height / 2 < ball.y) {
      enemy.y += 3;
    } else {
      enemy.y -= 3;
    }

    // Spielerbewegung
    canvas.onmousemove = handleMouseMove;

    // Punktestand anzeigen
    scoreDisplay.textContent = `Spieler: ${playerScore} | Gegner: ${enemyScore}`;
  }

  /**
   * Startet das Spiel bei Klick auf den Start-Button.
   *
   * @returns {void}
   */
  function handleStartClick() {
    startScreen.style.display = 'none';
    gameScreen.style.display = 'block';
    resetBall();
    gameInterval = setInterval(draw, 1000 / 60);
  }

  /**
   * Beendet das Spiel bei Klick auf den Beenden-Button.
   *
   * @returns {void}
   */
  function handleQuitClick() {
    clearInterval(gameInterval);
    gameScreen.style.display = 'none';
    startScreen.style.display = 'block';
  }

  // Events binden
  startButton.addEventListener('click', handleStartClick);
  quitButton.addEventListener('click', handleQuitClick);
});
