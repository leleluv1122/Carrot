<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script>
	var id = '${idd}'; //게시글 번호


	$('[name=answerInsertBtn]').click(function() { //댓글 등록 버튼 클릭시 
		var insertData = $('[name="answerInsertForm"]').serialize(); //commentInsertForm의 내용을 가져옴
		answerInsert(insertData); //Insert 함수호출(아래)
	});

	//댓글 목록 
	function answerList() {
		$
				.ajax({
					url : '/answer/list',
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
											/* a += '<a onclick="answerDelete('
													+ value.id
													+ ');" style="cursor:pointer;float:right;"> <b>삭제 </b></a> </div>'; */
											a += '작성일: ' + value.writedate;
											a += '<div class="commentContent'+value.id+'"> <p> 답변 : '
													+ value.content + '</p>';
											a += '</div></div>';
										});

						$(".answerList").html(a);
					}
				});
	}

	 //댓글 등록
	function answerInsert(insertData) {
		$.ajax({
			url : '/answer/insert',
			type : 'post',
			data : insertData,
			success : function(data) {
				if (data == 1) {
					answerList(); //댓글 작성 후 댓글 목록 reload
					$('[name=content]').val('');
				}
			}
		});
	}

	//댓글 삭제 
	function answerDelete(pid) {
		$.ajax({
			url : '/answer/delete/' + pid,
			type : 'post',
			success : function(data) {
				if (data == 1)
					answerList(id); //댓글 삭제후 목록 출력 
			}
		});
	}

	$(document).ready(function() {
		answerList(); //페이지 로딩시 댓글 목록 출력 
	});
</script>
