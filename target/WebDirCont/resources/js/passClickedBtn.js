function chgAction()
{
    if($(clicked).hasClass('submitBtn')) {
        document["main-form"].action = "/contentExtract?name=btn1";
    }
    else if( $(clicked).hasClass('DBLoadBtn')) {
    	document["main-form"].action = "/contentExtract?name=btn2";
    }
}
