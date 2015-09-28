package pokerEnums;



public enum eRank {

	 
		   A(14) , J(11), K(13), Q(12), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7),
		   EIGHT(8), NINE(9), TEN(10);
			private int value;
			 
			private eRank(int value) {
				this.value = value;
			}
			private eRank rank;
			
			public eRank getRank() {
				return rank;
			}

			public void setRank(eRank rank) {
				this.rank = rank;
			}

}