package bhtweb.dto;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import bhtweb.dbaccess.CommentMapper;
import bhtweb.dbaccess.UserAccountMapper;
import bhtweb.entities.BHTComment;
import bhtweb.entities.BHTUserAccount;

public class CommentDTO {
	
	private Integer id;
	private String content;
	private List<CommentDTO> commentChilds;
	
	private Date postTimeStamp;
	private String userAvatarURL;
	private Integer userID;
	private String userName;
	
	private CommentMapper commentMapper;
	private UserAccountMapper userAccountMapper;
	
	private Boolean commentSoftDeleted;
	
	private Boolean commentHidden;
	
	private Boolean commentApproved;
	
	private Integer parentCommentID;
	
	private Integer postID;
	
	public CommentDTO (BHTComment comment) {
		try {
			//Tạo ra các mapper truy cập vào DB.
			commentMapper = new CommentMapper();
			userAccountMapper = new UserAccountMapper();
			
			this.id = comment.getCommentID();
			this.content = comment.getCommentContent();
			
			//Ở đây phải truy cập đến CSDL để lấy về được các comment con (do trong CSDL chỉ có trường parentID mà thôi).
			List<BHTComment> entityComments = commentMapper.getCommentByParentId(this.id);
			
			//Nếu có phần tử con thì mới cần convert qua DTO, không có thì không cần convert gì cả.
			if (entityComments != null || entityComments.size() > 0) {
				this.commentChilds = entityComments.stream().map(CommentDTO::new).collect(Collectors.toList());
			}else {
				this.commentChilds = null;
			}
			
			postTimeStamp = comment.getCommentDtm();
			
			this.userID = comment.getUserId();
			
			//Đầu tiên là phải lấy ra được thông tin của người dùng đã gửi comment.
			BHTUserAccount userAccount = userAccountMapper.getAccountById(comment.getUserId());
			userAvatarURL = userAccount.getProfilePictureURL();
			userName = userAccount.getUserName();
			
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	public Integer getParentCommentID() {
		return parentCommentID;
	}

	public void setParentCommentID(Integer parentCommentID) {
		this.parentCommentID = parentCommentID;
	}

	public Integer getPostID() {
		return postID;
	}



	public void setPostID(Integer postID) {
		this.postID = postID;
	}



	public Boolean getCommentHidden() {
		return commentHidden;
	}

	public void setCommentHidden(Boolean commentHidden) {
		this.commentHidden = commentHidden;
	}

	public Boolean getCommentApproved() {
		return commentApproved;
	}



	public void setCommentApproved(Boolean commentApproved) {
		this.commentApproved = commentApproved;
	}



	public Boolean getCommentSoftDeleted() {
		return commentSoftDeleted;
	}

	public void setCommentSoftDeleted(Boolean commentSoftDeleted) {
		this.commentSoftDeleted = commentSoftDeleted;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public List<CommentDTO> getCommentChilds() {
		return commentChilds;
	}
	public void setCommentChilds(List<CommentDTO> commentChilds) {
		this.commentChilds = commentChilds;
	}
	public Date getPostTimeStamp() {
		return postTimeStamp;
	}
	public void setPostTimeStamp(Date postTimeStamp) {
		this.postTimeStamp = postTimeStamp;
	}
	public String getUserAvatarURL() {
		return userAvatarURL;
	}
	public void setUserAvatarURL(String userAvatarURL) {
		this.userAvatarURL = userAvatarURL;
	}
	public Integer getUserID() {
		return userID;
	}
	public void setUserID(Integer userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}