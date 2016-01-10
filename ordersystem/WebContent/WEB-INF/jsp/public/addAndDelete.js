$(function() {
	$('#addnew').click(function() {
		window.location.href = "saveUI.html";
	});
});

function del(id) {
	if (confirm("确定要删除吗？")) {
		var url = "list.html";
		window.location.href = url;
	}
}