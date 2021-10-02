window.addEventListener('load', function () {
    let tabla = document.querySelector('#info');
    let obtener = function(){
        const url = '/odontologos';
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            tabla.innerHTML = "";
            data.forEach(element => {
                tabla.innerHTML +=
                `<tr id=${element.id}>
                    <td class="id">${element.id}</td>
                    <td class="matricula">${element.matricula}</td>
                    <td class="nombre">${element.nombre}</td>
                    <td class="apellido">${element.apellido}</td>
                    <td class="editar"><button>EDITAR</button></td>
                    <td class="eliminar"><button>ELIMINAR</button></td>
                </tr>`;
            } );
            confirmacionEliminar( );
            editar( );
        } );
    }
    obtener();

    let agregar = function( ) {
        const formulario = document.querySelector('#agregar form');
        formulario.addEventListener('submit', function (event) {
            event.preventDefault();
            const formData = {
                nombre: document.querySelector('#nombre').value,
                apellido: document.querySelector('#apellido').value,
                matricula: document.querySelector('#matricula').value,
            };

            const modalError = document.querySelector('#agregar .error');
            if(formData.nombre == "" || formData.apellido == "" || formData.matricula == "") {
                modalError.classList.remove("none");
            } else {
                const url = '/odontologos';
                const settings = {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData)
                }
                fetch(url, settings)
                .then(response => {
                    response.json();
                    obtener();
                    resetUploadForm();
                } );
                modalError.classList.add("none");
            }
        } );
    }
    agregar();

    function resetUploadForm( ) {
        document.querySelector('#nombre').value = "";
        document.querySelector('#apellido').value = "";
        document.querySelector('#matricula').value = "";
    }

    let confirmacionEliminar = function( ) {
        let btnEliminar = document.querySelectorAll('.eliminar');
        for(let i = 0; i < btnEliminar.length; i++) {
            btnEliminar[i].addEventListener("click", function() {
                let id = this.parentNode.id;
                if(confirm("Eliminar?")) {  
                    eliminar(id);
                }
            } );
        }
    }

    let eliminar = function(id) {
        const url = '/odontologos/eliminar/'+ id;
        const settings = {
            method: 'DELETE'
        }
        fetch(url,settings)
        .then(response => {
            response.json();
            obtener();
        } )
    }

    let editar = function( ) {
        let modificar = document.querySelector('#modificar');
        let btnModificar = document.querySelectorAll('.editar');
        for(let i = 0; i < btnModificar.length; i++) {
            btnModificar[i].addEventListener("click", function() {
                modificar.classList.remove("none")
                document.querySelector('#id_modificar').value = this.parentNode.querySelector('.id').innerHTML;
                document.querySelector('#nombre_modificar').value = this.parentNode.querySelector('.nombre').innerHTML;
                document.querySelector('#apellido_modificar').value = this.parentNode.querySelector('.apellido').innerHTML;
                document.querySelector('#matricula_modificar').value = this.parentNode.querySelector('.matricula').innerHTML;
            } )
        }

        let formModificar = document.querySelector('#modificar form');
        formModificar.addEventListener('submit', function (event) {
            event.preventDefault();

            const formData = {
                id: document.querySelector('#id_modificar').value,
                nombre: document.querySelector('#nombre_modificar').value,
                apellido: document.querySelector('#apellido_modificar').value,
                matricula: document.querySelector('#matricula_modificar').value,
            };

            const modalError = document.querySelector('#modificar .error');
            if(formData.nombre == "" || formData.apellido == "" || formData.matricula == "") {
                modalError.classList.remove("none");
            } else {
                modalError.classList.add("none");
                modificar.classList.add("none");

                const url = '/odontologos/actualizar/';
                const settings = {
                    method: 'PUT',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(formData)
                }
                fetch(url,settings)
                .then(response => { 
                    response.json();
                    obtener();
                    resetUploadForm();
                } );
            }
        } )
    }
} );