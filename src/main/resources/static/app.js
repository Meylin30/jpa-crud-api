
const apiBase = '/dogs';

async function loadDogs() {
  try {
    const res = await fetch(apiBase);
    if (!res.ok) throw new Error(`HTTP ${res.status}`);
    const dogs = await res.json();
    renderDogs(dogs);
  } catch (err) {
    console.error('Error loading dogs:', err);
  }
}

function renderDogs(dogs) {
  const container = document.getElementById('dogs-container');
  container.innerHTML = '';
  dogs.forEach(dog => {
    const card = document.createElement('div');
    card.className = 'dog-card';
    card.innerHTML = `
      <h3>${escapeHtml(dog.name)}</h3>
      <p><strong>Breed:</strong> ${escapeHtml(dog.breed)}</p>
      <p><strong>Age:</strong> ${dog.age}</p>
    `;
    container.appendChild(card);
  });
}

function escapeHtml(s) {
  return String(s).replace(/[&<>"']/g, c => ({
    '&': '&amp;',
    '<': '&lt;',
    '>': '&gt;',
    '"': '&quot;',
    "'": '&#39;'
  }[c]));
}

document.addEventListener('DOMContentLoaded', loadDogs);
