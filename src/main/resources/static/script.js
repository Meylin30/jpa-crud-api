const apiBase = '/dogs';
const dogList = document.getElementById('dog-list');

// Load all dogs
async function loadDogs() {
  dogList.innerHTML = '<p>Loading dogs...</p>';
  try {
    const res = await fetch(apiBase);
    const dogs = await res.json();

    if (!dogs.length) {
      dogList.innerHTML = '<p>No dogs available.</p>';
      return;
    }

    dogList.innerHTML = dogs.map(dog => `
      <div class="box">
        <div class="image" onclick="goToDetails(${dog.id})">
          <i class="fas fa-dog fa-5x dog-avatar"></i>
        </div>
        <div class="content">
          <h3>${dog.name}</h3>
          <p>${dog.breed} | Age: ${dog.age}</p>
          <div class="dog-buttons">
            <button class="edit-btn" onclick="editDog(${dog.id})"><i class="fas fa-edit"></i> Edit</button>
            <button class="delete-btn" onclick="deleteDog(${dog.id})"><i class="fas fa-trash"></i> Delete</button>
          </div>
        </div>
      </div>
    `).join('');

  } catch (err) {
    console.error('Error fetching dogs:', err);
    dogList.innerHTML = '<p>Error loading dogs.</p>';
  }
}

// Go to details page
function goToDetails(id) {
  window.location.href = `details.html?id=${id}`;
}

// Delete dog
async function deleteDog(id) {
  if (!confirm('Are you sure you want to delete this dog?')) return;
  try {
    const res = await fetch(`${apiBase}/${id}`, { method: 'DELETE' });
    if (res.ok) {
      alert('Dog deleted successfully!');
      loadDogs();
    } else {
      alert('Failed to delete dog.');
    }
  } catch (err) {
    console.error('Delete failed:', err);
  }
}

// Edit dog
async function editDog(id) {
  try {
    const res = await fetch(`${apiBase}/${id}`);
    const dog = await res.json();

    document.getElementById('edit-id').value = dog.id;
    document.getElementById('edit-name').value = dog.name;
    document.getElementById('edit-breed').value = dog.breed;
    document.getElementById('edit-age').value = dog.age;
    document.getElementById('edit-description').value = dog.description || '';

    document.getElementById('edit-modal').style.display = 'flex';
  } catch (err) {
    console.error('Error loading dog for edit:', err);
  }
}

// Close modal
function closeModal() {
  document.getElementById('edit-modal').style.display = 'none';
}

// Save edited dog
document.getElementById('edit-form').addEventListener('submit', async (e) => {
  e.preventDefault();

  const id = document.getElementById('edit-id').value;
  const dog = {
    name: document.getElementById('edit-name').value.trim(),
    breed: document.getElementById('edit-breed').value.trim(),
    age: parseInt(document.getElementById('edit-age').value),
    description: document.getElementById('edit-description').value.trim()
  };

  try {
    const res = await fetch(`${apiBase}/${id}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(dog)
    });

    if (res.ok) {
      alert('Dog updated successfully!');
      closeModal();
      loadDogs();
    } else {
      alert('Error updating dog.');
    }
  } catch (err) {
    console.error('Update error:', err);
  }
});

// Search by name
document.getElementById('search-form').addEventListener('submit', async (e) => {
  e.preventDefault();
  const name = document.getElementById('search-box').value.trim();
  if (!name) return loadDogs();

  try {
    const res = await fetch(`${apiBase}/search?name=${encodeURIComponent(name)}`);
    const dogs = await res.json();
    dogList.innerHTML = dogs.map(dog => `
      <div class="box">
        <div class="image" onclick="goToDetails(${dog.id})">
          <i class="fas fa-dog fa-5x dog-avatar"></i>
        </div>
        <div class="content">
          <h3>${dog.name}</h3>
          <p>${dog.breed} | Age: ${dog.age}</p>
          <div class="dog-buttons">
            <button class="edit-btn" onclick="editDog(${dog.id})"><i class="fas fa-edit"></i> Edit</button>
            <button class="delete-btn" onclick="deleteDog(${dog.id})"><i class="fas fa-trash"></i> Delete</button>
          </div>
        </div>
      </div>
    `).join('');
  } catch (err) {
    console.error('Search error:', err);
  }
});

document.addEventListener('DOMContentLoaded', loadDogs);

