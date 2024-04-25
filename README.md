API使用範例

user
	*getall取得全部使用者
	http://localhost:8080/api/users
	
 	*new新增使用者
	http://localhost:8080/api/user/newUser
	{
	  "username": "andy",
	  "phone": "0912345678",
	  "email": "andy@gmail.com",
	  "password": "12345678",
	  "biography": "您好，我是吳先生~"
	}
 
	*update更新使用者資訊
	http://localhost:8080/api/user/update/d7423830-e326-4613-921f-07217850cabe
	{
	  "username": "王大陸"
	}

	*remove 移除使用者
	http://localhost:8080/api/user/remove/e7aadf4e-0649-4776-b308-96b2f11745f6

post
	*getall
	http://localhost:8080/api/posts

	*new
	http://localhost:8080/api/post/newPost?user_id=d7423830-e326-4613-921f-07217850cabe
	
	*update
	http://localhost:8080/api/post/update/3?user_id=e7aadf4e-0649-4776-b308-96b2f11745f6
	{
	  "content": "測試中..."
	}
	
	*remove
	http://localhost:8080/api/post/remove/1?user_id=d7423830-e326-4613-921f-07217850cabe

comment
	*getCommentByPost
	http://localhost:8080/api/comment?post_id=4
	
	
	*new
	http://localhost:8080/api/comment/newComment?user_id=d7423830-e326-4613-921f-07217850cabe&post_id=4
	{
	  "content": "555555555"
	}
	
	*update
	http://localhost:8080/api/post/update/3?user_id=e7aadf4e-0649-4776-b308-96b2f11745f6?post_id=0
	{
		"content": "123"
	}
	
	
	
	*remove
	http://localhost:8080/api/comment/remove/2?user_id=D7423830-e326-4613-921f-07217850cabe
	
	
