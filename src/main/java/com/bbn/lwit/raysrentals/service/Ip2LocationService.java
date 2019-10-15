package com.bbn.lwit.raysrentals.service;

import com.bbn.lwit.raysrentals.domain.VisitorLocation;

    import java.io.IOException;
    import java.io.RandomAccessFile;
    import java.net.InetAddress;
    import java.net.UnknownHostException;
    import java.nio.ByteBuffer;

public class Ip2LocationService {
    private class Metadata {
        int databasetype;
        int databasecolumn;
        int databaseyear;
        int databasemonth;
        int databaseday;
        long ipv4databasecount;
        long ipv4databaseaddr;
        long ipv6databasecount;
        long ipv6databaseaddr;
        long ipv4indexbaseaddr;
        long ipv6indexbaseaddr;
        int ipv4columnsize;
        int ipv6columnsize;
    }

    private static final int[] COUNTRY_POSITION = {0, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    private static final int[] REGION_POSITION = {0, 0, 0, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3};
    private static final int[] CITY_POSITION = {0, 0, 0, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4};
    private static final int[] ISP_POSITION = {0, 0, 3, 0, 5, 0, 7, 5, 7, 0, 8, 0, 9, 0, 9, 0, 9, 0, 9, 7, 9, 0, 9, 7, 9};
    private static final int[] LATITUDE_POSITION = {0, 0, 0, 0, 0, 5, 5, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5};
    private static final int[] LONGITUDE_POSITION = {0, 0, 0, 0, 0, 6, 6, 0, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6};
    private static final int[] DOMAIN_POSITION = {0, 0, 0, 0, 0, 0, 0, 6, 8, 0, 9, 0, 10, 0, 10, 0, 10, 0, 10, 8, 10, 0, 10, 8, 10};
    private static final int[] ZIPCODE_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 0, 7, 7, 7, 0, 7, 0, 7, 7, 7, 0, 7};
    private static final int[] TIMEZONE_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 8, 7, 8, 8, 8, 7, 8, 0, 8, 8, 8, 0, 8};
    private static final int[] NETSPEED_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8, 11, 0, 11, 8, 11, 0, 11, 0, 11, 0, 11};
    private static final int[] IDDCODE_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 12, 0, 12, 0, 12, 9, 12, 0, 12};
    private static final int[] AREACODE_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 13, 0, 13, 0, 13, 10, 13, 0, 13};
    private static final int[] WEATHERSTATIONCODE_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 14, 0, 14, 0, 14, 0, 14};
    private static final int[] WEATHERSTATIONNAME_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 15, 0, 15, 0, 15, 0, 15};
    private static final int[] MCC_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 9, 16, 0, 16, 9, 16};
    private static final int[] MNC_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 10, 17, 0, 17, 10, 17};
    private static final int[] MOBILEBRAND_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 18, 0, 18, 11, 18};
    private static final int[] ELEVATION_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 11, 19, 0, 19};
    private static final int[] USAGETYPE_POSITION = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12, 20};

    private RandomAccessFile fileHandle;
    private Metadata metadata;

    public Ip2LocationService(String file) throws IOException {
        fileHandle = new RandomAccessFile(file, "r");

        metadata = new Metadata();
        metadata.databasetype = read8(1);
        metadata.databasecolumn = read8(2);
        metadata.databaseyear = read8(3);
        metadata.databasemonth = read8(4);
        metadata.databaseday = read8(5);
        metadata.ipv4databasecount = read32(6);
        metadata.ipv4databaseaddr = read32(10);
        metadata.ipv6databasecount = read32(14);
        metadata.ipv6databaseaddr = read32(18);
        metadata.ipv4indexbaseaddr = read32(22);
        metadata.ipv6indexbaseaddr = read32(26);
        metadata.ipv4columnsize = metadata.databasecolumn << 2;
        metadata.ipv6columnsize = 16 + ((metadata.databasecolumn - 1) << 2);
    }

//    private static long ip2long(String ip) throws UnknownHostException {
//        return ByteBuffer.allocate(Integer.BYTES).put(InetAddress.getByName(ip).getAddress()).getInt(0);
//    }

    private static long ip2long(String strIP) throws UnknownHostException  {
        long[] ip = new long[4];
        int position1 = strIP.indexOf(".");
        int position2 = strIP.indexOf(".", position1 + 1);
        int position3 = strIP.indexOf(".", position2 + 1);
        ip[0] = Long.parseLong(strIP.substring(0, position1));
        ip[1] = Long.parseLong(strIP.substring(position1 + 1, position2));
        ip[2] = Long.parseLong(strIP.substring(position2 + 1, position3));
        ip[3] = Long.parseLong(strIP.substring(position3 + 1));
        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    private int read8(long position) throws IOException {
        fileHandle.seek(position - 1);

        return fileHandle.read();
    }

    private long read32(long position) throws IOException {
        fileHandle.seek(position - 1);

        long[] data = new long[4];
        for (int i = 0; i < data.length; i++) {
            data[i] = fileHandle.read();
        }

        return data[3] << 24 | data[2] << 16 | data[1] << 8 | data[0];
    }

    private float readFloat(long position) throws IOException {
        fileHandle.seek(position - 1);

        int[] data = new int[4];
        for (int i = 0; i < data.length; i++) {
            data[i] = fileHandle.read();
        }

        return Float.intBitsToFloat(data[3] << 24 | data[2] << 16 | data[1] << 8 | data[0]);
    }

    private String readString(long position) throws IOException {
        fileHandle.seek(position);

        int i = fileHandle.read();
        char[] data;

        try {
            data = new char[i];

            for (int j = 0; j < i; j++) {
                data[j] = ((char) fileHandle.read());
            }
        } catch (NegativeArraySizeException e) {
            return null;
        }

        return String.copyValueOf(data);
    }

    public VisitorLocation query(String ip) throws IOException {
        VisitorLocation result = new VisitorLocation().ip(ip);

        if (ip == null || ip.equals("")) {
            return result;
        }

        long low = 0;
        long high = metadata.ipv4databasecount;
        long mid;
        long ipfrom;
        long ipto;
        long ipno;

        try {
            ipno = ip2long(ip);
        } catch (UnknownHostException e) {
            return result;
        }

        if (ipno == 4294967295L) {
            ipno -= 1;
        }

        while (low <= high) {
            mid = (low + high) / 2;
            long rowOffset = metadata.ipv4databaseaddr + mid * metadata.databasecolumn * 4;
            ipfrom = read32(rowOffset);
            ipto = read32(rowOffset + metadata.databasecolumn * 4);

            if (ipno >= ipfrom && ipno < ipto) {
                long offset;

                if (COUNTRY_POSITION[metadata.databasetype] != 0) {
                    offset = read32(rowOffset + 4 * (COUNTRY_POSITION[metadata.databasetype] - 1));
                    result.setCountryCode(readString(offset));

                    offset += 3;
                    result.setCountryName(readString(offset));
                }

                if (REGION_POSITION[metadata.databasetype] != 0) {
                    offset = read32(rowOffset + 4 * (REGION_POSITION[metadata.databasetype] - 1));
                    result.setRegion(readString(offset));
                }

                if (CITY_POSITION[metadata.databasetype] != 0) {
                    offset = read32(rowOffset + 4 * (CITY_POSITION[metadata.databasetype] - 1));
                    result.setCity(readString(offset));
                }

                if (LATITUDE_POSITION[metadata.databasetype] != 0) {
                    offset = rowOffset + 4 * (LATITUDE_POSITION[metadata.databasetype] - 1);
                    result.setLatitude(readFloat(offset));
                }

                if (LONGITUDE_POSITION[metadata.databasetype] != 0) {
                    offset = rowOffset + 4 * (LONGITUDE_POSITION[metadata.databasetype] - 1);
                    result.setLongitude(readFloat(offset));
                }

                if (ZIPCODE_POSITION[metadata.databasetype] != 0) {
                    offset = read32(rowOffset + 4 * (ZIPCODE_POSITION[metadata.databasetype] - 1));
                    result.setZipcode(readString(offset));
                }

                if (TIMEZONE_POSITION[metadata.databasetype] != 0) {
                    offset = read32(rowOffset + 4 * (TIMEZONE_POSITION[metadata.databasetype] - 1));
                    result.setTimezone(readString(offset));
                }
                break;
            }

            if (ipno < ipfrom) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }
}
