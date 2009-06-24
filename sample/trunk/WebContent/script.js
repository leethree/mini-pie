var minipie = {
	detail:{"value":"Value", "type":"Type", "primary":"Primary"},
	permission:{"permission":"Permission"},
	rel:{"relationship":"Relationships"},
	address:{"zipcode":"ZIP code"},
	basic:{"displayName":"Name", "nickname":"Nickname", "birthday":"Birthday", "gender":"Gender",
		"note":"Notes"},
	profile:{"birthyearPermission":"Permission for birth year", 
			"birthdayPermission":"Permission for birth date",
			"genderPermission":"Permission for gender",
			"addascontactpermission":"Permission for adding-as-contact"}
};

minipie.onEditTypeChange = function(code) {
	var index=document.getElementById("edit_type").selectedIndex;
	var idtr=document.getElementById("edit_id");
	var field=document.getElementById("edit_field");
	if (index==0) {
		idtr.style.display="none";
		field.innerHTML="";
		minipie.addOptions(field, minipie.basic);
		if (code==1)
			minipie.addOptions(field, minipie.profile);
		if (code==2)
			minipie.addOptions(field, minipie.rel);
	} else {
		idtr.style.display="";
		field.innerHTML="";
		minipie.addOptions(field, minipie.detail);
		if (index==1)
			minipie.addOptions(field, minipie.address);
		if (code==1)
			minipie.addOptions(field, minipie.permission);
	}
	if(code==1)
		minipie.onEditFieldChange();
};

minipie.onEditFieldChange = function() {
	var field=document.getElementById("edit_field");
	var selected=field.options[field.selectedIndex].value;
	var perm=document.getElementById("edit_value_perm");
	var addperm=document.getElementById("edit_value_addperm");
	var value=document.getElementById("edit_value");
	
	if (selected=="permission"||selected=="birthyearPermission"||
			selected=="birthdayPermission"||selected=="genderPermission"){
		value.style.display="none";
		perm.style.display="";
		addperm.style.display="none";
	} else if (selected=="addascontactpermission"){
		value.style.display="none";
		perm.style.display="none";
		addperm.style.display="";
	} else {
		value.style.display="";
		perm.style.display="none";
		addperm.style.display="none";
	}
}

minipie.onAddTypeChange = function() {
	var index=document.getElementById("add_type").selectedIndex;
	var zipcode=document.getElementById("add_zipcode");
	if (index==0)
		zipcode.style.display="";
	else
		zipcode.style.display="none";
};

minipie.addOptions = function(field, array) {
	for (var key in array) {
		var o=document.createElement('option');
		o.value=key;
		o.text=array[key];
		field.add(o, null);
	}
};