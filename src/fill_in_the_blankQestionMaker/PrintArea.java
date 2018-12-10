package fill_in_the_blankQestionMaker;
import java.util.Random;

public class PrintArea {
	// 難易度の定数
	static final int EASY             = 0;
	static final int NORMAL		     = 1;
	static final int DIFFICULT       = 2;
	static final int PREPOSITION     = 3;

	//static int difficulty = 0;
	int boxNumber;

	private String[] data;

	Random rnd = new Random();

	static String[] exclusion = { "About" , "about" , "Aboard" , "aboard", "Above" , "above" , "Across", "across" ,
									"After" , "after" , "Against" , "against" , "Along" , "along" , "Alongside" , "alongside" ,
									"Amid" , "amid" , "Among", "among" , "Anti" , "anti" ,"Around" , "around" ,
									"As" , "as" , "At" , "at" , "Bar" , "bar" , "Before" , "before" ,
									"Behind" , "behind" , "Below" , "below" , "Beneath" , "beneath" , "Beside" , "beside" ,
									"Besides" , "besides" , "Between" , "between" , "Beyond" , "beyond" , "But" , "but" ,
									"By" , "by" , "Considering" , "considering" , "Despite" , "despite" , "Down" , "down" ,
									"During" , "during" , "Except" , "Except" , "For" , "for" , "From" , "from" ,
									"In" , "in" ,"Inside" , "inside" , "Into" , "into" , "Less" , "less" ,
									"Like" , "like" , "Minus" , "minus" , "Near" , "near" , "Notwithstanding" , "notwithstanding" ,
									"Of" , "of" , "Off" , "off" , "On" , "on" , "Onto" , "onto" ,
									"Opposite" , "opposite" , "Out" , "out" , "Outside" , "outside" , "Over" , "over" ,
									"Pace" , "pace" , "Past" , "past" , "Pending" , "pending" , "Per" , "per" ,
									"Plus" , "plus" , "Re" , "re" , "Regarding" , "regarding" , "Round" , "round" ,
									"Save" , "save" , "Saving" , "saving" , "Since" , "since" , "Than" , "than" ,
									"Through" , "through" , "Throughout" , "throughout" , "Till" , "till" , "To" , "to" ,
									"Touching" , "touching" , "Toward" , "toward" , "Under" , "under" , "Underneath" , "underneath" ,
									"Unless" , "unless" , "Unlike" , "unlike" , "Until" , "until" , "Up" , "up" ,
									"Versus" , "versus" , "Via" , "via" , "Vice" , "vice" , "With" , "with" ,
									"Within" , "within" , "Without" , "without"};
	String answer;

	void makePrint( int index , String[] data ){
		boolean num[] = new boolean[data.length];
		switch ( index ){
			case EASY:
				//boolean num[] = new boolean[data.length];
				boxNumber = (int)Math.ceil((double)data.length/10);
				//System.out.println(boxNumber);
				for( int i = 0 ; i<num.length ; i++ ){
					num[i]=false;
				}
				for( int count = 0; count < boxNumber; ){
					int ran = rnd.nextInt(data.length);
					if( num[ran]==false ){
						num[ran] = true;
						count++;
					}
				}
				//num = null;
				break;
			case NORMAL:
				//boolean num[] = new boolean[data.length];
				boxNumber = (int)Math.ceil((double)data.length/5);
				for( int i = 0 ; i<num.length ; i++ ){
					num[i]=false;
				}
				for( int count = 0; count < boxNumber; ){
					int ran = rnd.nextInt(data.length);
					if( num[ran]==false ){
						num[ran] = true;
						count++;
					}
				}
				//num = null;
				break;
			case DIFFICULT:
				//boolean num[] = new boolean[data.length];
				boxNumber = (int)Math.ceil((double)data.length/2);
				for( int i = 0 ; i<num.length ; i++ ){
					num[i]=false;
				}
				for( int count = 0; count < boxNumber; ){
					int ran = rnd.nextInt(data.length);
					if( num[ran]==false ){
						num[ran] = true;
						count++;
					}
				}
				//num = null;
				break;
			case PREPOSITION:
				for( int i = 0; i < data.length; i++ ){
					for( int j = 0; j<exclusion.length && num[i]==false; j++ ){
						//System.out.println(data[i]);
						//System.out.println(exclusion[j]);
						if( data[i].equals(exclusion[j]) ){
							//System.out.println("data"+data[i]+"exclusion"+exclusion[j]);
							num[i] = true;
						}
					}
				}
				break;
		}
		int count = 1;
		answer = "";
		for( int i = 0; i < data.length; i++ ){
			String space = "";
			if( num[i]==true ){
				//for( int j = 0; j<data[i].length(); j++ ){
					//space += "   ";
				//}
				for(int j = 0; j<5; j++){
					space += "   ";
				}
				if( data[i].endsWith(".") ){
					int last = data[i].lastIndexOf(".");
					data[i] = data[i].substring(0, last);
					answer += count+"."+ data[i] +" ";
					data[i] = "("+count+"."+space+").   ";
				}else if( data[i].endsWith(",") ){
					int last = data[i].lastIndexOf(",");
					data[i] = data[i].substring(0, last);
					answer += count+"."+ data[i] +" ";
					data[i] = "("+count+"."+space+"), ";
				}else{
					answer += count+"."+data[i]+" ";
					data[i] = "("+count+"."+space+") ";
				}
				count++;
				//System.out.println(data[i]);
			}
		}
		num = null;
		this.data = data;
	}

	String[] getData(){
		return data;
	}

	String getAnswer(){
		return answer;
	}
}
