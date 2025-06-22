import L from 'leaflet';

window.addEventListener('load', function () {
  const mapContainer = document.getElementById('map');
  if (mapContainer) {
    const map = L.map(mapContainer).setView([20, 0], 2);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '© OpenStreetMap contributors',
    }).addTo(map);
  }
});

// Inhalt zu figcaptions hinzufügen
document.addEventListener('DOMContentLoaded', function () {
  const fcaptions = document.getElementsByTagName('figcaption');
  for (const fcap of fcaptions) {
    let txt = 'Ich bin ein wichtiger Beispieltext.\n\n';
    for (let i = 0; i < 20; i++) {
      txt += 'Ich bin ein Beispieltext. ';
    }
    fcap.innerText = txt;
  }
  const startScreen = document.getElementById('start-screen');
  const gameScreen = document.getElementById('game-screen');
  const startBtn = document.getElementById('start-button');
  const quitBtn = document.getElementById('quit-button');
  const canvas = document.getElementById('game-canvas');
  const ctx = canvas.getContext('2d');

  let playerScore = 0;
  let enemyScore = 0;
  let gameInterval;

  startBtn.addEventListener('click', () => {
    startScreen.style.display = 'none';
    gameScreen.style.display = 'block';
    startGame();
  });

  quitBtn.addEventListener('click', () => {
    clearInterval(gameInterval);
    gameScreen.style.display = 'none';
    startScreen.style.display = 'block';
  });
});
