$(function() {
	$('#myform').validate({
		rules: {
			name: {
				required: true,
				minlength: 3,
				maxlength: 30
			},
			username: {
				required: true
			},
			email: {
				required: true
			},
			password: {
				required: true,
				minlength: 8,
				maxlength: 16
			}
		},
		messages: {
			name: {
				required: "Por favor, digite seu nome",
				minlength: "No mínimo {0} caracteres",
				maxlength: "No maximo {0} caracteres"
			},
			username:{
				required: "Por favor, digite seu email",
			},
			email: {
				required: "Por favor, digite seu email",
			},
			password: {
				required: "Por favor, digite sua senha",
				minlength: "No mínimo {0} caracteres",
				maxlength: "No maximo {0} caracteres"
			}
		}
	})
})