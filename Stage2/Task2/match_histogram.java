import ij.ImagePlus;
import ij.process.ImageProcessor;
import ij.plugin.filter.PlugInFilter;
import java.awt.Color;
import ij.*;


public class match_histogram implements PlugInFilter {
    public int setup(String args, ImagePlus im) {
        return DOES_RGB;
    }
    public void run(ImageProcessor ip) {
        int M = ip.getWidth();
        int N = ip.getHeight();

        float[] red = new float[256];
        float[] green = new float[256];
        float[] blue = new float[256];

        Color color;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
               color = new Color(ip.getPixel(j,i));
               red[color.getRed()] += 1;
               green[color.getGreen()] += 1;
               blue[color.getBlue()] += 1;
            }
        }
        for (int j = 1; j < red.length; j++) {
            red[j] = red[j - 1] + red[j];
            green[j] = green[j - 1] + green[j];
            blue[j] = blue[j - 1] + blue[j];
        } 
        for (int j = 0; j < red.length; j++) {
            red[j] = red[j] / (M * N);
            green[j] = green[j] / (M * N);
            blue[j] = blue[j] / (M * N);
        }
        double[] benchmark_histogram_red = { 0.2777778,0.2777778,0.2777778,0.2777778,0.27778846,0.27778846,0.2777778,0.27778846,0.27778846,0.27778846,0.27778846,0.27779916,0.27778846,0.27778846,0.2778312,0.27778846,0.27779916,0.27785257,0.27778846,0.2778312,0.27787393,0.27778846,0.2778953,0.27797008,0.27780983,0.27793804,0.2782372,0.2778312,0.27799144,0.27897435,0.27786323,0.27803418,0.2803312,0.2778846,0.27815172,0.28232905,0.27797008,0.2784188,0.28527778,0.27810898,0.2788675,0.2889423,0.2784081,0.27964744,0.29285255,0.27891025,0.28083333,0.2971154,0.27963674,0.28254274,0.30149573,0.28037393,0.2845406,0.30568376,0.2814423,0.28725427,0.31037393,0.2825107,0.29130343,0.31459403,0.28371796,0.29575855,0.31875,0.28485042,0.30056623,0.32292736,0.28628206,0.3053846,0.32705128,0.28794873,0.30963674,0.33134615,0.2901282,0.31396368,0.3353205,0.29240385,0.3182906,0.33948717,0.2952564,0.32221153,0.34366453,0.29810897,0.32635683,0.34758547,0.30110043,0.33023503,0.35142094,0.3045406,0.33427352,0.3555983,0.30826923,0.33826923,0.36036325,0.3116987,0.34182692,0.3649252,0.31503206,0.3457372,0.36985043,0.31856838,0.3493376,0.3744765,0.32192308,0.3530983,0.37942308,0.32478634,0.35666665,0.38409188,0.32786325,0.36037394,0.3892094,0.3309936,0.3640171,0.39438033,0.33456197,0.3679594,0.39955127,0.33764958,0.37194446,0.40451923,0.34055555,0.3759081,0.4096047,0.3440919,0.37981838,0.41462606,0.34701923,0.38357905,0.41905984,0.35004273,0.38744658,0.42363247,0.3525748,0.39070514,0.42857906,0.355,0.39429486,0.43366453,0.35780984,0.39818376,0.43856838,0.3601282,0.40199786,0.44356838,0.36279914,0.4054594,0.44787392,0.36547008,0.40902779,0.45264956,0.3679487,0.41255343,0.45780984,0.3705128,0.41634616,0.46238247,0.3726175,0.4196047,0.4674466,0.37516025,0.42278847,0.4729701,0.3771581,0.42582265,0.47832265,0.37982905,0.42904913,0.4840064,0.38230768,0.4319658,0.4894124,0.38435897,0.43528846,0.49514958,0.38658118,0.4383547,0.5007906,0.38871795,0.44168803,0.50648504,0.39087605,0.4449466,0.51274574,0.39325854,0.44826922,0.5184402,0.39561966,0.4515064,0.5242094,0.3973718,0.45460472,0.5296474,0.39930555,0.4576282,0.5352671,0.4011218,0.46082264,0.5415385,0.40299144,0.46430555,0.54764956,0.40485042,0.4676282,0.55327994,0.4068269,0.47070512,0.5585791,0.40892094,0.47402778,0.56438035,0.41102564,0.476891,0.5698932,0.41306624,0.4800641,0.5759936,0.41508546,0.48347223,0.58123934,0.41701922,0.48685896,0.58615386,0.41909188,0.49040598,0.5918803,0.4207799,0.49443376,0.59807694,0.42264956,0.4984722,0.6045406,0.42420942,0.50330126,0.61128205,0.42574787,0.50819445,0.6186966,0.42754275,0.51342946,0.6263889,0.42911324,0.5183868,0.63393164,0.43096155,0.523953,0.6408013,0.43254274,0.52939105,0.6478098,0.43482906,0.5344338,0.65510684,0.43695512,0.5396581,0.66221154,0.4390171};
        double[] benchmark_histogram_green = {0.5450107,0.6697329,0.44089743,0.55107903,0.6775321,0.44317308,0.5570406,0.6847436,0.44498932,0.5621795,0.692735,0.44693375,0.5673932,0.7004167,0.44910255,0.5730449,0.7080983,0.4515812,0.579391,0.7160577,0.4542094,0.5851923,0.7244658,0.45654914,0.59107906,0.7314637,0.45925215,0.5968269,0.73712605,0.461859,0.60336536,0.74141026,0.46451923,0.6099359,0.7446154,0.46724358,0.6166667,0.74732906,0.46982905,0.6232051,0.74956197,0.4721154,0.630235,0.7513675,0.47487178,0.6379701,0.7527564,0.4774466,0.64681625,0.7538141,0.480406,0.65665597,0.7546795,0.48351496,0.6654594,0.7554167,0.4871047,0.67502135,0.7559936,0.49089745,0.68393165,0.7565278,0.49477565,0.6929167,0.7569017,0.49892095,0.7023718,0.75714743,0.50293803,0.7125427,0.7574252,0.50698715,0.7220299,0.7577885,0.5110791,0.7302137,0.75811964,0.5155128,0.73736113,0.7582906,0.5199893,0.7437607,0.7586325,0.52469015,0.74877137,0.75891024,0.52875,0.75287396,0.7591987,0.5330983,0.75527775,0.75943375,0.5375534,0.75706196,0.75972223,0.54205126,0.7583333,0.7599466,0.5463675,0.7593376,0.7602351,0.55110043,0.7600641,0.7605235,0.5555021,0.7607158,0.7608867,0.56036323,0.7611004,0.76119655,0.5651282,0.76161325,0.76141024,0.57027775,0.7619124,0.7617842,0.57497865,0.76219016,0.76219016,0.57981837,0.76266026,0.7625641,0.5847863,0.7630128,0.7629914,0.59030986,0.7633654,0.7632586,0.5965064,0.76371795,0.7635577,0.6026389,0.7640278,0.76398504,0.6092201,0.76440173,0.76452994,0.6161645,0.7648718,0.7649145,0.6234509,0.76529914,0.7652671,0.6303953,0.7657372,0.7657265,0.6379701,0.7662607,0.76632476,0.64490384,0.7667628,0.76684827,0.6528205,0.7675107,0.7674359,0.66133547,0.76813036,0.7680449,0.67054486,0.7688034,0.7685684,0.67988247,0.769359,0.7691453,0.6902137,0.76990384,0.7698825,0.701047,0.7706731,0.7705769,0.71121794,0.7713568,0.7714423,0.7222009,0.7722863,0.77232903,0.73239315,0.77321583,0.7731196,0.7419017,0.7741987,0.7740919,0.7496581,0.77534187,0.77518165,0.7569017,0.7765705,0.77649575,0.7645513,0.7780235,0.77786326,0.7715171,0.7795406,0.77926284,0.7769338,0.7811432,0.7807158,0.781485,0.78268164,0.7818056,0.7851175,0.783953,0.7828312,0.787906,0.78523505,0.78371793,0.79010683,0.7862714,0.78463674,0.7923291,0.78726494,0.78549147,0.7945406,0.7882158,0.78639954,0.79728633,0.78910255,0.78717947,0.8005235,0.79009616,0.78800213,0.8048718,0.7911859,0.7888782,0.8106517,0.79267097,0.78996795,0.8179487,0.79412395,0.7911752,0.82772434,0.7957906,0.7923291,0.84121794,0.7977778,0.7937179,0.8578526,0.800406,0.7952137,0.87661326,0.8038141,0.79688036,0.89566237,0.8086645,0.79903847,0.9128739,0.8147115,0.80225426,0.92962605,0.82190174,0.8061645,0.94542736,0.82958335,0.8111004,0.9600107,0.8382158};
        double[] benchmark_histogram_blue = {0.8177671,0.9730876,0.8498611,0.82513887,0.98372865,0.86522436,0.83239317,0.9917735,0.8850641,0.8409081,0.9965064,0.90726495,0.8513996,0.9988141,0.9278419,0.8659509,0.9995406,0.9475641,0.8834081,0.9998397,0.9654701,0.9044338,0.99995726,0.98061967,0.92520297,1,0.99028844,0.9433654,1,0.9955983,0.9609615,1,0.99825853,0.9757265,1,0.99932694,0.98714745,1,0.9997436,0.9944231,1,0.9999466,0.99792737,1,0.99998933,0.99914527,1,1,0.9997329,1,1,0.99990386,1,1,0.99997866,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};
        
        int red_index;
        int green_index;
        int blue_index;
        double red_value;
        double green_value;
        double blue_value;
        double adj_red_value;
        int[] new_intensities = new int[3];
        for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    double diff_red = 2;
                    double diff_green = 2;
                    double diff_blue = 2;
                    color = new Color(ip.getPixel(j,i));
                    red_index = color.getRed();
                    green_index = color.getGreen();
                    blue_index = color.getBlue();
                    
                    red_value = red[red_index];
                    green_value = green[green_index];
                    blue_value = blue[blue_index];

                    for (int h = 0; h < 256; h++) {
                        if (Math.abs(benchmark_histogram_red[h] - red_value) < diff_red) {
                            diff_red = Math.abs(benchmark_histogram_red[h] - red_value);
                            new_intensities[0] = h;
                        }
                        if (Math.abs(benchmark_histogram_green[h] - green_value) < diff_green) {
                            diff_green = Math.abs(benchmark_histogram_green[h] - green_value);
                            new_intensities[1] = h;
                        }
                        if (Math.abs(benchmark_histogram_blue[h] - blue_value) < diff_blue) {
                            diff_blue = Math.abs(benchmark_histogram_blue[h] - blue_value);
                            new_intensities[2] = h;
                        }
                    }
                    ip.putPixel(j, i, new_intensities);
                }
        } 
    }
    
}