<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	var id = '${id}'; //게시글 번호


	$('[name=commentInsertBtn]').click(function() { //댓글 등록 버튼 클릭시 
		var insertData = $('[name=commentInsertForm]').serialize(); //commentInsertForm의 내용을 가져옴
		commentInsert(insertData); //Insert 함수호출(아래)
	});

	//댓글 목록 
	function commentList() {
		$
				.ajax({
					url : '/comment/list',
					type : 'get',
					data : {
						'id' : id
					},
					success : function(data) {
						
						var a = '';
						$
								.each(
										data,
										function(key, value) {
											a += '<div class="commentArea" style="border-bottom:1px solid darkgray; margin-bottom: 15px;">';
											a += '<div class="commentInfo'+value.id+'">'
													+ '작성자 : <a href="/shop/users/' + value.user.id + '">' + value.user.nickname + "</a>";
											a += '<a onclick="commentDelete('
													+ value.id
													+ ');" style="cursor:pointer;float:right;"> <b>삭제 </b></a> </div>';
											a += '작성일: ' + value.writedate;
											a += '<div class="commentContent'+value.id+'"> <p> 내용 : '
													+ value.content + '</p>';
											a += '</div></div>';
										});
						$(".commentList").html(a);
					}
				});
	}

	 //댓글 등록
	function commentInsert(insertData) {
		$.ajax({
			url : '/comment/insert',
			type : 'post',
			data : insertData,
			success : function(data) {
				if (data == 1) {
					commentList(); //댓글 작성 후 댓글 목록 reload
					$('[name=content]').val('');
				}
			}
		});
	}

	//댓글 삭제 
	function commentDelete(pid) {
		$.ajax({
			url : '/comment/delete/' + pid,
			type : 'post',
			success : function(data) {
				if (data == 1)
					commentList(id); //댓글 삭제후 목록 출력 
			}
		});
	}

	$(document).ready(function() {
		commentList(); //페이지 로딩시 댓글 목록 출력 
	});
</script>
