<template>
    <div>
      <h1>出库操作</h1>
      <select v-model="selectedWarehouse">
        <option value="成品仓库">成品仓库</option>
        <option value="材料仓库">材料仓库</option>
        <option value="临时仓库">临时仓库</option>
        <option value="配料仓">配料仓</option>
      </select>
      <input v-model="productNumber" placeholder="产品号" @input="searchProduct">
      <select v-if="productOptions.length > 0" v-model="selectedProduct">
        <option v-for="option in productOptions" :key="option.id" :value="option">{{ option.productNumber }}</option>
      </select>
      <input v-model="quantity" placeholder="数量">
      <input v-model="barcode" placeholder="扫码输入国标号">
      <button @click="submitOutbound">提交出库</button>
      <p>{{ message }}</p>
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
          <tr v-for="product in currentWarehouseProducts" :key="product.id">
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
  import { ref } from 'vue';
  import axios from 'axios';
  
  const selectedWarehouse = ref('成品仓库');
  const productNumber = ref('');
  const productOptions = ref<any[]>([]);
  const selectedProduct = ref<any | null>(null);
  const quantity = ref(0);
  const barcode = ref('');
  const message = ref('');
  const currentWarehouseProducts = ref<any[]>([]);
  
  const searchProduct = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/api/products/productNumber/${productNumber.value}`);
      productOptions.value = response.data;
    } catch (error) {
      console.error('Error searching product:', error);
    }
  };
  
  const submitOutbound = async () => {
    try {
      if (selectedProduct.value) {
        const productId = selectedProduct.value.id;
        await axios.post(`http://localhost:8080/api/products/updateQuantity/${productId}/-${quantity.value}`);
        message.value = `${productNumber.value} 出库成功`;
        // 刷新当前仓库产品列表
        // 这里需要根据实际情况添加根据仓库查询产品的接口
        const response = await axios.get('http://localhost:8080/api/products');
        currentWarehouseProducts.value = response.data;
      }
    } catch (error) {
      console.error('Error submitting outbound:', error);
    }
  };
  </script>