let form = document.querySelector("#formtask")

function create(e) {
	e.preventDefault()

	const task = document.querySelector("#task").value

	fetch('tasks', {
		method: 'POST',
		body: JSON.stringify({
			name: task
		}),
		headers: {
			'Content-Type': "application/json; charset=utf-8"
		}
	}).then(response => {
		if (response.status == 201) {
			alert('Tarefa criada com sucesso!')
			location.href = location.href
		} else if (response.status == 418) {
			alert('Preencha o nome da tarefa')
		}

	})

}

function read() {
	fetch('tasks')
		.then(response => response.json())
		.then(function(response) {
			let cont = 1;
			for (let i = 0; i < response.length; i++) {

				$('table tbody').append(`
                    <tr>
                    	<th>${cont++}</th>
                        <th scope="row">${response[i].name}</th>
                        <td><button type="button" class="btn btn-warning" data-toggle="modal" data-target="#exampleModal" onclick="readById('${response[i].id}')"><i class="far fa-edit"></i></button></td>
                        <td><button type="button" class="btn btn-danger" onclick="deleteTask('${response[i].id}')"><i class="far fa-trash-alt"></i></button></td>
                    </tr>
                `)

			}

		})
}

function readById(id) {
	fetch(`tasks/${id}`)
		.then(response => response.json())
		.then(function(response) {
			$("#taskName").val(response.name)
			$("#taskId").val(response.id)
		})
}

function update() {
	if (confirm('Comfirma a atualização do item')) {
		let id = $("#taskId").val()
		let taskName = $("#taskName").val()

		fetch(`tasks/${id}`, {
			method: 'PUT',
			body: JSON.stringify({
				name: taskName
			}),
			headers: {
				'Content-Type': "application/json; charset=utf-8"
			}
		}).then(response => {
			if (response.status == 200) {
				alert('Tarefa editada com sucesso!')
				$('#exampleModal').modal('hide');
				location.href = location.href
			} else if (response.status == 418) {
				alert('Preencha o nome da tarefa')
			} else {
				alert('Erro ao se atualizar a tarefa')
			}

		})
	}
}

function deleteTask(id) {
	if (confirm('Deseja deletar essa tarefa?')) {
		fetch(`tasks/${id}`, {
			method: 'DELETE'
		})
			.then(response => {
				if (response.status == 204) {
					alert('Tarefa deletada com sucesso')
					location.href = location.href
				} else if (response.status == 404) {
					alert('Não existe tarefa cadastada com esse id')
				} else {
					alert('Erro ao deletar a tarefa')
				}
			})
	}

}


form.addEventListener('submit', create)
read()