using System;
using System.IO;
using System.Text.RegularExpressions;

namespace TestGetALink
{
    class Program
    {
        static void Main(string[] args)
        {
            try
            {
                // Get the folder path
                Console.Write("Input the folder path: ");
                var folderPath = Console.ReadLine().Trim();

                // Start processing
                Console.WriteLine("Processing...");

                Match mHref;
                Match mSrc;
                string hRefPattern = "href\\s*=\\s*(?:[\"'](?<1>[^\"']*)[\"']|(?<1>\\S+))";
                string srcPattern = "src\\s*=\\s*(?:[\"'](?<1>[^\"']*)[\"']|(?<1>\\S+))";

                // Get all file in folder path
                var listFileNames = Directory.GetFiles(folderPath);
                foreach (var fileName in listFileNames)
                {
                    using (var file = new StreamReader(fileName))
                    {
                        using (var saveFile = new StreamWriter(fileName + "extract.txt"))
                        {
                            string line;
                            while ((line = file.ReadLine()) != null)
                            {
                                mHref = Regex.Match(line, hRefPattern, RegexOptions.IgnoreCase | RegexOptions.Compiled);
                                while (mHref.Success)
                                {
                                    var hrefValue = mHref.Groups[1];
                                    saveFile.WriteLine(hrefValue);
                                    mHref = mHref.NextMatch();
                                }

                                mSrc = Regex.Match(line, srcPattern, RegexOptions.IgnoreCase | RegexOptions.Compiled);
                                while (mSrc.Success)
                                {
                                    var srcValue = mSrc.Groups[1];
                                    saveFile.WriteLine(srcValue);
                                    mSrc = mSrc.NextMatch();
                                }
                            }
                        }
                    }
                }
            }
            catch (Exception ex)
            {
                Console.WriteLine(ex.Message);
            }

            Console.WriteLine("Press any key to exit.");
            Console.ReadKey();
        }
    }
}
