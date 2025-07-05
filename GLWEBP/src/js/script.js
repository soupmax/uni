import L from 'leaflet';
import './pingpong.js';

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
});
