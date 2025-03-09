<template>
    <div>
      <h1>仓库产品或材料详细信息</h1>
      <select v-model="selectedWarehouse" @change="fetchProducts">
        <option value="成品仓库">成品仓库</option>
        <option value="材料仓库">材料仓库</option>
        <option value="临时仓库">临时仓库</option>
        <option value="配料仓">配料仓</option>
      </select>
      <table>
        <thead>
          <tr>
            <th>名称</th>
            <th>产品号</th>
            <th>颜色</th>
            <th>数量</th>
            <th>国标号</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="product in products" :key="product.id">
            <td>{{ product.name }}</td>
            <td>{{ product.productNumber }}</td>
            <td>{{ product.color }}</td>
            <td>{{ product.quantity }}</td>
            <td>{{ product.nationalStandardNumber }}</td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script lang="ts" setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  
  const selectedWarehouse = ref('成品仓库');
  const products = ref<any[]>([]);
  
  const fetchProducts = async () => {
    try {
      // 这里需要根据实际情况添加根据仓库查询产品的接口
      const response = await axios.get('http://localhost:8080/api/products');
      products.value = response.data;
    } catch (error) {
      console.error('Error fetching products:', error);
    }
  };
  
  onMounted(() => {
    fetchProducts();
  });
  </script>