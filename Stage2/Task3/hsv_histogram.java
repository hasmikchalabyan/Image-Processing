import ij.ImagePlus;
import ij.IJ;
import ij.process.ImageProcessor;
import ij.plugin.filter.PlugInFilter;
import java.awt.Color;

public class hsv_histogram implements PlugInFilter {
 public int setup(String args, ImagePlus im) {
  return DOES_RGB;
 }

 public void run(ImageProcessor ip) {
  int width = ip.getWidth(), 
        height = ip.getHeight(),
        totalH = 0,
        totalS = 0,
        totalV = 0;
  float coeff = width * height;
  float[] hsv = new float[3];
  Color colorRGB;
  float[] h = new float[360],
            s = new float[101],
            v = new float[101];

  for (int row = 0; row < height; row++)
   for (int col = 0; col < width; col++) {
    colorRGB = new Color(ip.getPixel(col, row));
    Color.RGBtoHSB(colorRGB.getRed(),colorRGB.getGreen(),colorRGB.getBlue(), hsv);
    h[(int) (hsv[0] * 360) % 360]  += 1;
    s[(int) (hsv[1] * 100)] +=1;
    v[(int) (hsv[2] * 100)] +=1;
   }
   String hvals = String.format("%3s ", "H:");
   String svals = String.format("%3s ", "S:");
   String vvals = String.format("%3s ", "V:");
  for (int i = 0; i < 360; i++) {
      totalH += h[i];
      h[i] = totalH / coeff;

      hvals += String.format("%.5f ", h[i]);
  }
  for (int i = 0; i < 101; i++) {
      totalS += s[i];
      totalV += v[i];  

      s[i] = totalS / coeff;
      v[i] = totalV / coeff;    

      svals += String.format("%.5f ", s[i]);
      vvals += String.format("%.5f ", v[i]);      
  }
  IJ.log(hvals + "\n" + svals + "\n" + vvals + "\n");
 }
}