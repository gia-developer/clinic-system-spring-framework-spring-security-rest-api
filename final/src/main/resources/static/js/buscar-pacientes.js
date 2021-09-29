window.addEventListener('load', function () {
    let tabla = document.querySelector('#info');
    let buscar = document.querySelector("#buscarForm");
    buscar.addEventListener("submit", function( e ) {
        e.preventDefault( );
        let id = document.querySelector("#buscar").value;

        const url = '/pacientes/' + id;
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            tabla.innerHTML +=
            `<tr id=${data.id}>
                <td class="id">${data.id}</td>
                <td class="nombre">${data.nombre}</td>
                <td class="apellido">${data.apellido}</td>
                <td class="dni">${data.dni}</td>
                <td class="domicilio">${data.domicilio}</td>
                <td class="fechaDeAlta">${data.fechaDeAlta}</td>
            </tr>`;
        } );
    } );
} )