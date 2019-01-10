package main;

public enum Mp3Enum {
	TAG_ZONE_LEN (128),
	TAG_LEN (3),
	TITLE_LEN (30),
	AUTHOR_LEN (30),
	ALBUM_LEN (30),
	YEAR_LEN (4),
	COMMENT_LEN (30),
	STYLE_LEN (1);

	
	private int value;
	Mp3Enum(int value) {	// pas public, car enum  est privé
		this.value=value;
	}
	public int getValue() {
		return value;
	}
	
}
