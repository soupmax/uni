import L from 'leaflet';
import './pingpong.js';

/**
 * Initialisiert die Weltkarte mit Leaflet.js.
 * Zeigt bei Ladefehlern eine Nachricht im Map-Container.
 *
 * @returns {void}
 */
function initializeMap() {
  const mapContainer = document.getElementById('map');
  if (!mapContainer) return;

  const map = L.map(mapContainer).setView([20, 0], 2);

  const tiles = L.tileLayer(
    'https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png',
    {
      attribution: '© OpenStreetMap contributors',
    },
  );

  // Fehlerbehandlung bei Ladeproblemen
  tiles.on('tileerror', () => {
    mapContainer.innerHTML = `
      <div style="padding: 2em; text-align: center; color: #900;">
        <strong>Fehler beim Laden der Karte.</strong><br />
        Bitte überprüfen Sie Ihre Internetverbindung.
      </div>`;
  });

  tiles.addTo(map);
}

/**
 * Fügt jedem <figcaption> auf der Seite Beispieltext hinzu.
 * Wird verwendet auf der Bilder-Seite zur automatischen Generierung von Demo-Beschreibungen.
 *
 * @returns {void}
 */
function fillImageCaptions() {
  const fcaptions = document.getElementsByTagName('figcaption');
  for (const fcap of fcaptions) {
    let txt = 'Ich bin ein wichtiger Beispieltext.\n\n';
    for (let i = 0; i < 20; i++) {
      txt += 'Ich bin ein Beispieltext. ';
    }
    fcap.innerText = txt;
  }
}

/**
 * Füllt alle <aside>-Elemente mit einem simulierten Lade-Spinner.
 * Verwendet für Seiten mit noch nicht implementierten oder dynamisch ladbaren Inhalten.
 *
 * @returns {void}
 */
function fillAsidesWithLoader() {
  const asides = document.getElementsByTagName('aside');
  for (const aside of asides) {
    aside.classList.add('sidebar-loader');
    aside.innerHTML = `
      <h2>Inhalt wird geladen...</h2>
      <div class="spinner-container">
        <div class="spinner"></div>
      </div>
      <p class="loading-note">Bitte warten...</p>
    `;
  }
}

// Initialisierungen ausführen
window.addEventListener('load', initializeMap);
document.addEventListener('DOMContentLoaded', () => {
  fillImageCaptions();
  fillAsidesWithLoader();
});
