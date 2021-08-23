let form = document.querySelector("#myform")

function create(e) {
	
	e.preventDefault()

	const name = document.querySelector("#name").value
	const email = document.querySelector("#email").value
	const password = document.querySelector("#password").value

	fetch('users', {
		method: 'POST',
		body: JSON.stringify({
			name: name,
			email: email,
			password: password
		}),
		headers: {
			'Content-Type': "application/json; charset=utf-8"
		}
	}).then(response => {
		if (response.status == 201) {
			alert('Usuário criado com sucesso!')
			window.location = '/login'
		} else if (response.status == 400) {
			alert('Já existe um usuário cadastrado com esse email');
			window.location = '/login'
		}else if (response.status == 418) {
			alert('Preencha todos os campos corretamente')
		}
	})
}

form.addEventListener('submit', create)
