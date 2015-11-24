import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class ClubAssociation {
	public static void main(String[] args) {
		try {
			File directory = new File("JavaClass");
			File[] fileList = directory.listFiles();
			for (File file : fileList) {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
				String sLine;
				String sLine2;
				String textOld = "";
				while ((sLine = bufferedReader.readLine()) != null) {
					textOld += sLine + "\r\n";
					if (!sLine.isEmpty()) {
						sLine = sLine.trim().replaceAll("\\s+", " ");
						if (sLine.indexOf("assoc") != -1) {
							String[] wordList = sLine.split(" ");
							int l = wordList.length;
							String s1 = wordList[--l];
							String str = s1.concat(".java");
							for (File files : fileList) {
								if (files.getName().equals(str)) {
									BufferedReader bufferedReader2 = new BufferedReader(new FileReader(files));
									String textOld2 = "";
									while ((sLine2 = bufferedReader2.readLine()) != null) {
										textOld2 += sLine2 + "\r\n";
										if (sLine2.indexOf("assoc") != -1) {
											String[] wordList2 = sLine2.split(" ");
											int len = wordList2.length;
											String s2 = wordList2[--len];
											String str2 = s2.concat(".java");
											if (file.getName().equals(str2)) {
												String removecolon1 = sLine.replace(";", "");
												String[] wordListCur = removecolon1.split(" ");
												String removecolon2 = sLine2.replace(";", "").trim();
												String[] wordListOth = removecolon2.split(" ");
												String star = "*";
												String rpl = "* @assoc " + wordListOth[4] + " - " + wordListCur[4] + " "
														+ wordListCur[5];
												textOld = textOld.replace(sLine, rpl);
												textOld2 = textOld2.replace(sLine2, "");
											}
										}
									}
									FileWriter fileWriter = new FileWriter(files);
									fileWriter.write(textOld2);
									fileWriter.close();
								}
							}
						}
					}
				}
				FileWriter fileWriter2 = new FileWriter(file);
				fileWriter2.write(textOld);
				fileWriter2.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}