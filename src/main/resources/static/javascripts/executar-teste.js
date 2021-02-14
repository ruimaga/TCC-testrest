$('#confirmTeste').on('show.bs.modal', function (event) {
	var botao = $(event.relatedTarget);
	var nome = botao.data('url');
	var url = botao.data('url-delete');
	
	var modal = $(this);
	var form = modal.find('form');
	form.attr('action', url);
	modal.find('.modal-body span').html('Tem certeza que deseja Testar ?');
	
	
});
