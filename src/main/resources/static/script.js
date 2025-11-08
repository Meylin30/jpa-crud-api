const apiBase = '/dogs';

async function loadDogs() {
  const container = document.getElementById('dog-list');
  container.innerHTML = '<p>Loading...</p>';

  try {
    const res = await fetch(apiBase);
    const dogs = await res.json();

    container.innerHTML = dogs.map(dog => `
      <div class="box" onclick="goToDetails(${dog.id})">
        <div class="image">
          <img src="${dog.imageUrl || 'https://place-puppy.com/200x200'}" alt="${dog.name}">
        </div>
        <div class="content">
          <h3>${dog.name}</h3>
          <p>${dog.breed}</p>
        </div>
      </div>
    `).join('');

  } catch (err) {
    container.innerHTML = '<p>Error loading dogs.</p>';
    console.error(err);
  }
}

function goToDetails(id) {
  window.location.href = `details.html?id=${id}`;
}

document.addEventListener('DOMContentLoaded', loadDogs);
