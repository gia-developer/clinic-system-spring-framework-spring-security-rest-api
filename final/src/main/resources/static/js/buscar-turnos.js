window.addEventListener('load', function () {
    let tabla = document.querySelector('#info');
    let buscar = document.querySelector("#buscarForm");
    buscar.addEventListener("submit", function( e ) {
        e.preventDefault( );
        let id = document.querySelector("#buscar").value;

        const url = '/turnos/' + id;
        const settings = {
            method: 'GET'
        }
        fetch(url,settings)
        .then(response => response.json())
        .then(data => {
            tabla.innerHTML +=
            `<tr id=${data.id}>
                <td class="id">${data.id}</td>
                <td class="paciente">${data.paciente.id}</td>
                <td>${data.paciente.nombre}</td>
                <td class="odontologo">${data.odontologo.id}</td>
                <td>${data.odontologo.nombre}</td>
                <td class="fecha">${data.fecha}</td>
            </tr>`;
        } );
    } );
} )