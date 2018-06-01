
public class DrawLine {
	/*
	 * On a screen of width w (bytes), draw a line from (x1, y) to (x2, y) bits / pixels
	 * screen is represented by an array of bytes[].
	 * 
	 * The position of the pixel in the screen array can be determined as w * y * 8 + x.
	 * To draw a line from x1 to x2, check if it contains full bytes. could be set directly at indexes using 0xFF.
	 * The rest (mod) can be set using masks. 00001111 && 11110000
	 * 
	 * */
	
	public static void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int startByte = x1 / 8, startOffset = x1 % 8, endByte = x2 / 8, endOffset = x2 % 8;
		if(startOffset > 0) startByte++;
//		this is not byte(8 bits), but 32 bits.
//		int startMask = (1 << startOffset) - 1, endMask = ~((1 << (8 - endOffset)) - 1);
		
		// Shift byte 8 bits. using 0xFF shift. not on integers.
		byte startMask = (byte) (0xFF >> startOffset), endMask = (byte) ~(0xFF >> (endOffset + 1));
		for(int i = startByte; i < endByte; i++) {
			screen[(width / 8) * y + i] |= 0xFF;
		}
		if(startByte - 1 == endByte) {
			// when in the same byte, AND the two masks to get the common 1s to set.
			int mask = startMask & endMask;
			// when setting the index, using OR.
			// if not sure, use an example and go thru it to make sure.!
			screen[(width / 8) * y + startByte - 1] |= mask;
		} else {
			if(startOffset != 0) screen[(width / 8) * y + startByte - 1] |= startMask;
			if(endOffset != 0) screen[(width / 8) * y + endByte] |= endMask;
		}
	}
	
	public static void drawLine2(byte[] screen, int width, int x1, int x2, int y) {
		int start_offset = x1 % 8;
		int first_full_byte = x1 / 8;
		if (start_offset != 0) {
			first_full_byte++;
		}
		
		int end_offset = x2 % 8;
		int last_full_byte = x2 / 8;
		if (end_offset != 7) {
			last_full_byte--;
		}
		
		// Set full bytes
		for (int b = first_full_byte; b <= last_full_byte; b++) {
			screen[(width / 8) * y + b] = (byte) 0xFF;
		}
		
		byte start_mask = (byte) (0xFF >> start_offset);
		byte end_mask = (byte) ~(0xFF >> (end_offset + 1));
		
		// Set start and end of line
		if ((x1 / 8) == (x2 / 8)) { // If x1 and x2 are in the same byte
			byte mask = (byte) (start_mask & end_mask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if (start_offset != 0) {
				int byte_number = (width / 8) * y + first_full_byte - 1;
				screen[byte_number] |= start_mask;
			}
			if (end_offset != 7) {
				int byte_number = (width / 8) * y + last_full_byte + 1;
				screen[byte_number] |= end_mask;
			} 
		}
	}
	
	
	public static int computeByteNum(int width, int x, int y) {
		return (width * y + x) / 8;
	}
	
	public static void printByte(byte b) {
		for (int i = 7; i >= 0; i--) {
			char c = ((b >> i) & 1) == 1 ? '1' : '_';
			System.out.print(c);
		}
	}
	
	public static void printScreen(byte[] screen, int width) {
		int height = screen.length * 8 / width;
		for (int r = 0; r < height; r++) {
			for (int c = 0; c < width; c+=8) {
				byte b = screen[computeByteNum(width, c, r)];
				printByte(b);
			}
			System.out.println("");
		}
	}
	
	public static void main(String[] args) {
		int width = 8 * 1;
		int height = 1;
		for (int r = 0; r < height; r++) {
			for (int c1 = 0; c1 < width; c1++) {
				for (int c2 = c1; c2 < width; c2++) {
					byte[] screen = new byte[width * height / 8];

					System.out.println("row: " + r + ": " + c1 + " -> " + c2);
					drawLine(screen, width, c1, c2, r);
					printScreen(screen, width);
					System.out.println("\n\n");
				}
			}
		}

	}

}
