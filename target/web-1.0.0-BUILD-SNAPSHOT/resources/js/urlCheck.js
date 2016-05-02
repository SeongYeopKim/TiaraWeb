$(function(e) {
	var windowLocation = window.location + "";
	var locationSplit = new Array();
	locationSplit = windowLocation.split('/');

	var locationSplitLength = locationSplit.length;

	switch (locationSplit[locationSplitLength - 1]) {
	case "list.tiara":
		$(".sideNav ul li:eq(1) a img").attr("src","resources/images/home/memberBtn_on.png");
		break;
	case "listJson.tiara":
		$(".sideNav ul li:eq(1) a img").attr("src","resources/images/home/memberBtn_on.png");
		break;
	case "signUp.tiara":
		$(".sideNav ul li:eq(1) a img").attr("src","resources/images/home/memberBtn_on.png");
		break;
	case "noticeView.tiara":
		$(".sideNav ul li:eq(2) a img").attr("src","resources/images/home/noticeBtn_on.png");
		break;
	case "GoUploadNotice.tiara":
		$(".sideNav ul li:eq(2) a img").attr("src","resources/images/home/noticeBtn_on.png");
		break;
	case "push.do":
		$(".sideNav ul li:eq(3) a img").attr("src","resources/images/home/pushBtn_on.png");
		break;
	case "goGalleryAdd.tiara":
		$(".sideNav ul li:eq(4) a img").attr("src","resources/images/home/galleryBtn_on.png");
		break;
	case "youtube.tiara":
		$(".sideNav ul li:eq(4) a img").attr("src","resources/images/home/galleryBtn_on.png");
		break;
	default:
		$(".sideNav ul li:eq(0) a img").attr("src","resources/images/home/homeBtn_on.png");
		break;
	}
	 
});